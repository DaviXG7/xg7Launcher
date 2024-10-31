package controlers

sealed class Versions {

    enum class Minecraft {
        V1_21_1,
        V1_20_6,

    }
    sealed class Server {
        enum class Spigot {
            V1_21_1,
            V1_20_1
        }
        enum class Paper {
            V1_21_1,
            V1_20_1
        }
    }

}