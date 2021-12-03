plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
            resources.srcDirs("input")
        }
    }

    wrapper {
        gradleVersion = "7.3"
    }
}
