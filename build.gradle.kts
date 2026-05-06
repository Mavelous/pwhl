import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21

plugins {
	kotlin("jvm") version "2.2.21"
	kotlin("plugin.serialization") version "2.2.21"
	application
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.google.guava:guava:32.1.2-jre")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
	implementation("com.google.code.gson:gson:2.10.1")
	implementation("org.json:json:20231013")
	implementation("org.jetbrains.kotlin:kotlin-reflect:2.2.21")
	implementation("org.flywaydb:flyway-core:10.10.0")
	implementation("org.flywaydb:flyway-database-postgresql:10.10.0")
	implementation("org.postgresql:postgresql:42.7.2")

	testImplementation(kotlin("test"))
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
}

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

kotlin {
	compilerOptions {
		jvmTarget.set(JVM_21)
	}
}

application {
	mainClass.set("us.mavelo.AppKt")
}
