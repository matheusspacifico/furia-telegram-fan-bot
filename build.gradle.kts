plugins {
    kotlin("jvm") version "2.1.20"
}

group = "com.furia"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.3.0")

    implementation("io.ktor:ktor-client-core:3.1.2")
    implementation("io.ktor:ktor-client-cio:3.1.2")

    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    implementation("ch.qos.logback:logback-classic:1.5.13")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}