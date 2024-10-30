package controlers

data class Profile(
    val name: String,
    val image: String,
    val path: String,
    val type: Type
) {
    enum class Type {
        MINECRAFT,
        SPIGOT
    }
}