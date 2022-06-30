plugins {
    kotlin("multiplatform") apply false
    kotlin("plugin.serialization")
}

allprojects {
    group = "com.kimond"
    version = "0.0.1"

    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

}

