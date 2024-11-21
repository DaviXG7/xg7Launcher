package controlers

val processes = mutableMapOf<Profile, Process>()


fun createProcess(profile: Profile, onClose: () -> Unit) {

    val processBuilder = ProcessBuilder("\"${getFile("assets\\jdks\\jdk${profile.javaVersion.versionName}").listFiles().first().path + "\\bin\\java.exe"}\" ${profile.additionalScript} ${profile.startScript}")

    processes[profile] = processBuilder.start()
}

fun main() {
    createProcess(ServerProfile("nome", "", Version.V1_21_1, Software.PAPER, JavaVersion.V8, "-jar server.jar", "")) {}
}