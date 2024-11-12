package controlers

enum class Software {
    PAPER,
    SPIGOT,
}

enum class JavaVersion(val versionName: String) {
    V8("8"),
    V11("11"),
    V17("17"),
    V21("21")
}

enum class Version(val versionName: String) {
    V1_21_1("1.21.1"),
    V1_20_6("1.20.6"),
    V1_19_4("1.19.4"),
    V1_18_2("1.18.2"),
    V1_17_1("1.17.1"),
    V1_16_5("1.16.5"),
    V1_15_2("1.15.2"),
    V1_14_4("1.14.4"),
    V1_13_2("1.13.2"),
    V1_12_2("1.12.2"),
    V1_11_2("1.11.2"),
    V1_10_2("1.10.2"),
    V1_9_4("1.9.4"),
    V1_8_8("1.8.8"),
    V1_7_10("1.7.10");

}