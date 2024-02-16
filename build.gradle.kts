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
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("org.json:json:20231013")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22")
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
