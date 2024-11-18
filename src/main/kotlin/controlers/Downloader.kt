package controlers

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.window.application
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import downloadWindow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.*
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL
import java.util.*
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream


suspend fun downloadJava(version: JavaVersion, onProgress: (Float) -> Unit) : File? {
    val url = when (version) {
        JavaVersion.V8 -> "https://download1529.mediafire.com/4wlujryfnt2gbL9wE5xbCrmcQyIQCZqAh2akewm_RTZm9JlZyaqut9eVO8UOWC88eRZg4H3DXhZkn4pF_QkzsMMgiGnwNvMPz12_X9ctLZaWmFCsrYkrxDZyoQ2rivV5Mg0QVDKyHZFFJtrFoc0tq6g0hEMkIKImdLTLzpPPwfQ4oTw/lp6e1u0x35jw4oa/zulujdk8.zip"
        JavaVersion.V11 -> "https://download1326.mediafire.com/73v06vc5q5xgJH3tZ-17mcYfc4V3yZDH3J5xyZFrP5iPPfK9dNWJb2TJsuzt3r0iPToR8f8VxMXQYLnNjoAERW3BpWfAlND3x4VncsmOFHZiPyemvgTqOWJ9HbrFBnfuzG6Y1TguU3ZIwtT-c2edhVxL-y0q3X7cSMd4KYmMEW5-ooE/go9bivc3m8440df/zulujdk11.zip"
        JavaVersion.V17 -> "https://download1589.mediafire.com/wpn2sgep8y0gsYS71QlSHKBnOSray0BUuYL0IoBy5pGKNVruj0zu_cLxBVzHg5CLGAGE8EmCk17JHB7vpEENLQ_L-6Zh75Bkjsso8PU_0FWRDuBzLP2y8A6zGvnnHVSYe-hxxTBMJ0RoXHAWm5pcJducHlVo75BN3oqr5ooGpr1B3b8/amelml7v9ch04l8/zulujdk17.zip"
        JavaVersion.V21 -> "https://download1323.mediafire.com/sitrdsjynqygvq5As099Qq4z-B0FZKtuUKufyL9_Uq27Flo2pfRp_cAKlCno82OfwGFHieoo4xl1Kv4IQp4USi0FfMHOcZQvS8TizuL-B42ggqJTYvygg2bt0w-wr5oOwnmssoGxfrxfCBwlV6BmKaFamcg3nhFFm8inseD7DNun64U/azl1dxtlyt56asc/zulujdk21.zip"
    }
    return download(url, getFolder("assets\\jdks").path + "\\jdk${version.versionName}.zip", onProgress)
}

suspend fun downloadSpigot(software: Software, version: Version, onProgress: (Float) -> Unit) : File? {

    val url = when (software) {
        Software.PAPER -> {

            val jsonObject = downloadJson("https://gist.githubusercontent.com/osipxd/6119732e30059241c2192c4a8d2218d9/raw/2364c68607b838bca04db0f5f6f233d1ae03fd05/paper-versions.json")
            jsonObject.getAsJsonObject("versions").get(version.versionName).asString

        }
        Software.SPIGOT -> {

            val jsonObject = downloadJson("https://download851.mediafire.com/wb4w75dtux9gU40M8-vbQ5uml7krRo2g2CeqSQ7v1pexL5qCjE2QFaMDPs6OPgHWKcGfGVLLBkprnxJWwEf8aP6LjFZos-8Reus2dhkFv_r3K233ZnbZXygJg8A_1MNxZsMp9_7f5hV-NSkVUH5HVhODPuXiQfgxR3RfYJrHlwtY7pE/jy6hllzppmy67gx/downloads.json")

            jsonObject.get(version.versionName).asString
        }
    }

    return download(url, getFolder("assets\\servers").path + "\\${software.name.lowercase(Locale.getDefault())}${version.versionName}.jar", onProgress)
}

suspend fun download(urlTarget: String, downloadPath: String, onProgress: (Float) -> Unit) : File? {
    return try {
        withContext(Dispatchers.IO) {
            val url = URI(urlTarget).toURL()
            val file = File(downloadPath)

            val connection = url.openConnection()
            val totalSize = connection.contentLengthLong
            val inputStream: InputStream = connection.getInputStream()
            val fos = FileOutputStream(file)

            var bytesDownloaded: Long = 0
            val buffer = ByteArray(8 * 1024)
            var bytesRead: Int

            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                fos.write(buffer, 0, bytesRead)
                bytesDownloaded += bytesRead

                val progress = bytesDownloaded / totalSize.toFloat()
                onProgress(progress)
            }

            inputStream.close()
            fos.close()

            // Se o arquivo for ZIP, faz o unzip e apaga o arquivo original
            if (file.extension == "zip") {
                unzip(file)
                file.delete()
            }

            file // Retorna o arquivo se tudo ocorrer bem
        }
    } catch (e: Exception) {
        e.printStackTrace()
        println("Erro ao fazer o download. Tente novamente.")
        null
    }
}

suspend fun downloadJson(url: String) : JsonObject {
    val connection = URI(url).toURL().openConnection() as HttpURLConnection
    return try {
        connection.requestMethod = "GET"
        connection.inputStream.bufferedReader().use { JsonParser.parseReader(it).asJsonObject }
    } finally {
        connection.disconnect()
    }
}

fun unzip(zipFile: File): File {
    val destDirectory = File(zipFile.parent, zipFile.nameWithoutExtension)
    if (!destDirectory.exists()) {
        destDirectory.mkdirs()
    }

    ZipInputStream(FileInputStream(zipFile)).use { zipIn ->
        var entry: ZipEntry? = zipIn.nextEntry
        while (entry != null) {
            val filePath = "${destDirectory.path}${File.separator}${entry.name}"
            if (!entry.isDirectory) {
                BufferedOutputStream(FileOutputStream(filePath)).use { bos ->
                    val bytesIn = ByteArray(4096)
                    var read: Int
                    while (zipIn.read(bytesIn).also { read = it } != -1) {
                        bos.write(bytesIn, 0, read)
                    }
                }
            } else {
                File(filePath).mkdirs()
            }
            zipIn.closeEntry()
            entry = zipIn.nextEntry
        }
    }
    return destDirectory
}

fun main() = application {
    downloadWindow(
        initDownload = { progressCallback ->
            downloadSpigot(Software.PAPER, Version.V1_21_1) { progressCallback(it) }
        },
        text = "Baixando PaperSpigot1.8.8"
        ,
        {
            println("Download finalizado")
            ::exitApplication.invoke()
        }
    )
}