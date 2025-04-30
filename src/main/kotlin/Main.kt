package com.furia

import com.furia.com.furia.config.EnvConfig
import com.furia.com.furia.handler.handleStartCommand
import com.furia.com.furia.handler.handleUpcomingCommand
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.logging.LogLevel

fun main() {
    val bot = bot {
        token = EnvConfig.BOT_TOKEN
        timeout = 30
        logLevel = LogLevel.Network.Body

        dispatch {
            handleStartCommand()
            handleUpcomingCommand()
        }
    }

    bot.startPolling()
}