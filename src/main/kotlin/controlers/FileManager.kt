package controlers

import java.io.File
import java.io.InputStream
import java.nio.file.Files

val jarFolder = File(Thread.currentThread().contextClassLoader.getResource("").toURI())

fun getFolder(path: String): File {
    val file = File(jarFolder, path)

    if (!file.exists()) {
        file.mkdirs()
    }

    return file
}

fun getFile(path: String): File {
    val file = File(jarFolder, path)

    if (!file.exists()) {
        Files.copy(getResource(path), file.toPath())
    }

    return file
}

fun getResource(resourcePath: String): InputStream {
    return File(Thread.currentThread().contextClassLoader.getResource(resourcePath).toURI()).inputStream()
}


fun main() {
    println("Pasta do JAR: $jarFolder")
}
