package controlers

abstract class Profile(
    open var name: String,
    open var image: String,
    open var pathFiles: String,
    open var javaVersion: JavaVersion
)

data class MinecraftProfile(
    override var name: String,
    override var image: String,
    override var pathFiles: String,
    override var javaVersion: JavaVersion
) : Profile(name, image, pathFiles, javaVersion)

data class ServerProfile(
    override var name: String,
    override var image: String,
    override var pathFiles: String,
    var software: Software,
    override var javaVersion: JavaVersion
) : Profile(name, image, pathFiles, javaVersion)
