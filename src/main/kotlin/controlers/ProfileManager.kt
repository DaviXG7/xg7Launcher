package controlers

import Screen

val profileList = listOf<Profile>()

fun createProfile(type: Screen, name: String, image: String, pathFiles: String, javaVersion: JavaVersion, software: Software): Profile? {

    return when (type) {
        Screen.PROFILE_MINECRAFT -> createServerProfile(name, image, pathFiles, javaVersion, software)
        Screen.PROFILE_SPIGOT -> createServerProfile(name, image, pathFiles, javaVersion, software)
        Screen.MAIN -> return null
    }
}

private fun createServerProfile(name: String, image: String, pathFiles: String, javaVersion: JavaVersion, software: Software): ServerProfile {

    val profile = ServerProfile(name,image,"profiles/$name",software,javaVersion);

    return ServerProfile("","","",Software.SPIGOT,JavaVersion.V17);

}