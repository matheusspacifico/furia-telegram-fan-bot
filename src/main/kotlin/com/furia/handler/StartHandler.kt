package com.furia.com.furia.handler

import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.callbackQuery
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.ParseMode
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton

fun Dispatcher.handleStartCommand() {
    val inlineKeyboard = InlineKeyboardMarkup.create(
        listOf(
            InlineKeyboardButton.CallbackData(text = "Start", callbackData = "start"),
            InlineKeyboardButton.CallbackData(text = "Upcoming", callbackData = "upcoming"),
            InlineKeyboardButton.CallbackData(text = "Live", callbackData = "live"),
            InlineKeyboardButton.CallbackData(text = "Torcida", callbackData = "torcida")
        )
    )

    val response = """
            🐾 Fala, guerreiro(a)! Aqui o fan chat bot da FURIA!
            
            Quer ficar por dentro dos *jogos de CS?* Veio ao lugar certo!
            */start* - Mostra essa mensagem novamente! 🤖
            */upcoming* - Próximas partidas da FURIA! 📆
            */live* - Confira se a FURIA está jogando! 🎮
            */torcida* - Torcida virtual da FURIA! 🎉
            
            *#VAIFURIA* 🔥🔥🔥
        """.trimIndent()

    command("start") {
        bot.sendMessage(ChatId.fromId(message.chat.id), response, ParseMode.MARKDOWN, replyMarkup = inlineKeyboard)
    }

    callbackQuery("start") {
        bot.sendMessage(ChatId.fromId(callbackQuery.message?.chat?.id ?: return@callbackQuery), response, ParseMode.MARKDOWN, replyMarkup = inlineKeyboard)
    }
}