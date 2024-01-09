plugins {
    alias(libs.plugins.jvm)

    application

    kotlin("plugin.serialization") version "1.9.22"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.guava)
    implementation("it.skrape:skrapeit:1.2.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("com.google.code.gson:gson:2.9.0")
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useKotlinTest("1.9.20")
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("us.mavelo.AppKt")
}
