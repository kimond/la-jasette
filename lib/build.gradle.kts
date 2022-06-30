plugins {
    kotlin("multiplatform")
    id("maven-publish")
    kotlin("plugin.serialization")
}

repositories {
    mavenLocal()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
            }
        }
    }
}

publishing {
    publications.withType<MavenPublication>().all {
        artifactId = "lajasette-lib"
    }
}

