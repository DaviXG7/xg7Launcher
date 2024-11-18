package controlers

import java.io.File
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.StandardCopyOption

val jarFolder = File(Thread.currentThread().contextClassLoader.getResource("").toURI())

fun assets() {
    getFolder("assets")
    getFolder("assets\\images")
    getFile("assets\\images\\logo.png")
}

fun getFolder(path: String): File {
    val file = File(jarFolder, path)

    if (!file.exists()) {
        file.mkdirs()
    }

    return file
}

fun exists(path: String): Boolean {
    return File(jarFolder, path).exists()
}

fun getFile(path: String): File {
    val file = File(jarFolder, path)

    if (!file.exists()) {
        Files.copy(getResource(path), file.toPath())
    }

    return file
}

fun copyFile(path: String, newPath: String) {
    Files.copy(File(path).toPath(), File(jarFolder, newPath).toPath(), StandardCopyOption.REPLACE_EXISTING)
}

fun getResource(resourcePath: String): InputStream {
    return File(Thread.currentThread().contextClassLoader.getResource(resourcePath).toURI()).inputStream()
}
fun writeContent(content: String, path: String) {
    File(jarFolder, path).writeText(content)
}





