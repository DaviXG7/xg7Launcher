package controlers

import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.URI
import java.net.URL


fun downloadJava(version: JavaVersion) {
    when (version) {
        JavaVersion.V8 -> {

            val url = URI("https://www.mediafire.com/file/lp6e1u0x35jw4oa/zulu8.82.0.21-ca-jdk8.0.432-win_x64.zip/file").toURL()
            val file = File("C:\\Users\\davis\\Documents\\testedownload\\testejdk8media.zip")

            val `is`: InputStream = url.openStream()
            val fos = FileOutputStream(file)

            var bytes: Int

            while ((`is`.read().also { bytes = it }) != -1) {
                println("downloading")
                fos.write(bytes)
            }

            `is`.close()

            fos.close()

        }

        JavaVersion.V11 -> TODO()
        JavaVersion.V17 -> TODO()
        JavaVersion.V21 -> TODO()
    }
}

fun main() {
    downloadJava(JavaVersion.V8)
}