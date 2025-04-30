package com.furia.com.furia.config

import io.github.cdimascio.dotenv.dotenv

object EnvConfig {
    private val dotenv = dotenv {
        ignoreIfMissing = true
    }

    val BOT_TOKEN = dotenv["BOT_TOKEN"] ?: throw IllegalArgumentException("Missing bot token in .env!")
}