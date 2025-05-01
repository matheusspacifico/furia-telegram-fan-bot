package com.furia.com.furia.handler

import com.furia.com.furia.menu.KeyboardMenu
import com.furia.com.furia.service.MatchService
import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.callbackQuery
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.ParseMode

fun Dispatcher.handleUpcomingCommand() {
    val matchService = MatchService()

    fun fetchUpcomingMatches(): String {
        val matches = matchService.fetchUpcomingMatches()

        if (matches.isEmpty()) {
            return "😿 Nenhuma partida da FURIA ou do Academy encontrada.\nFique ligado, em breve tem mais emoção! #VAIFURIA 🔥"
        }

        val furiaMatches = matches.map { match ->
            """
            ${match["emoji"]} *${match["furiaType"]} em ação!*
            *Evento:* ${match["event"]}
            *Adversário:* ${match["opponent"]}
            *Data:* ${match["formattedTime"]}
            """.trimIndent()
        }

        return StringBuilder().apply {
            append("*Próximos confrontos da FURIA:*\n\n")
            append(furiaMatches.joinToString("\n\n"))
            append("\n\n_Prepare a torcida, vista a camisa e marque na agenda!_")
            append("\n*#VAIFURIA* 🔥🔥🔥")
        }.toString()
    }

    command("upcoming") {
        val data = fetchUpcomingMatches()
        bot.sendMessage(ChatId.fromId(message.chat.id), data, ParseMode.MARKDOWN, replyMarkup = KeyboardMenu.createMainKeyboard())
    }

    callbackQuery("upcoming") {
        val data = fetchUpcomingMatches()
        val chatId = callbackQuery.message?.chat?.id ?: return@callbackQuery
        bot.sendMessage(ChatId.fromId(chatId), data, ParseMode.MARKDOWN, replyMarkup = KeyboardMenu.createMainKeyboard())
    }
}
