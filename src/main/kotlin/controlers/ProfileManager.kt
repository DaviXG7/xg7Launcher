package controlers

import Screen
import androidx.compose.runtime.Composable
import com.google.gson.GsonBuilder
import downloadWindow
import java.util.*

val profileList = mutableListOf<Profile>()

@Composable
fun createProfile(type: Screen, name: String, image: String, javaVersion: JavaVersion, version: Version, software: Software): Profile? {

    return when (type) {
        Screen.PROFILE_MINECRAFT -> createServerProfile(name, image, javaVersion, software,version)
        Screen.PROFILE_SPIGOT -> createServerProfile(name, image, javaVersion, software,version)
        Screen.MAIN -> return null
    }
}

@Composable
private fun createServerProfile(name: String, image: String, javaVersion: JavaVersion, software: Software, version: Version): ServerProfile {

    val profile = ServerProfile(name,image, version, software, javaVersion,"-jar server.jar", "")

    val gson = GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create()
    val json = gson.toJson(profile)

    getFolder("profiles")

    writeContent(json,"profiles\\$name.json").toString()

    getFolder("spigot")

    if (!exists("assets\\jdks\\jdk${javaVersion.versionName}")) {
        downloadWindow(
            initDownload = { progressCallback ->
                downloadJava(javaVersion) { progressCallback(it) }
            },
            text = "Baixando Java ${javaVersion.versionName}",
            {
                println("Download finalizado")
            }
        )
    }
    if (!exists("assets\\servers\\${software.name.lowercase(Locale.getDefault())}${version.versionName}.jar")) {
        downloadWindow(
            initDownload = { progressCallback ->
                downloadSpigot(software,version) { progressCallback(it) }
            },
            text = "Baixando ${software.name} ${version.versionName}",
            {
                println("Download finalizado")

                copyFile(jarFolder.path + "\\assets\\servers\\${software.name.lowercase(Locale.getDefault())}${version.versionName}.jar", "spigot\\$name\\server.jar")
                profileList.add(profile)
            }
        )
        return profile
    }

    copyFile(jarFolder.path + "\\assets\\servers\\${software.name.lowercase(Locale.getDefault())}${version.versionName}.jar", "spigot\\$name\\server.jar")

    profileList.add(profile)

    return profile

}

fun runProfile(profile: Profile) {

}

@Composable
fun coisa() {
    createServerProfile("test", "test", JavaVersion.V8, Software.PAPER, Version.V1_8_8)
}