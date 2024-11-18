package controlers

import com.google.gson.annotations.Expose

abstract class Profile(
    @Expose open var name: String,
    @Expose open var image: String,
    @Expose open var javaVersion: JavaVersion,
    @Expose open var minecraftVersion: Version,
    @Expose open var startScript: String,
    @Expose open var additionalScript: String
)

data class MinecraftProfile(
    override var name: String,
    override var image: String,
    override var minecraftVersion: Version,
    var listNames: List<String> = mutableListOf(),
    override var javaVersion: JavaVersion,
    override var startScript: String,
    override var additionalScript: String
) : Profile(name, image, javaVersion, minecraftVersion, startScript,"")

data class ServerProfile(
    override var name: String,
    override var image: String,
    override var minecraftVersion: Version,
    @Expose var software: Software,
    override var javaVersion: JavaVersion,
    override var startScript: String,
    override var additionalScript: String
) : Profile(name, image, javaVersion, minecraftVersion, startScript,"")
