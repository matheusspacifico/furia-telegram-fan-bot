plugins {
    kotlin("jvm") version "2.1.20"
    application
}

group = "com.furia"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.0.7")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    implementation("org.json:json:20250107")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("com.furia.MainKt")
}
