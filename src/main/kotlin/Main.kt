package com.furia

import com.furia.com.furia.config.EnvConfig
import com.furia.com.furia.handler.handleStartCommand
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch

fun main() {
    val bot = bot {
        token = EnvConfig.BOT_TOKEN
        dispatch {
            handleStartCommand()
        }
    }

    bot.startPolling()
}