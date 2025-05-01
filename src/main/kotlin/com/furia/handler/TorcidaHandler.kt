package com.furia.com.furia.handler

import com.furia.com.furia.menu.KeyboardMenu
import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.callbackQuery
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.ParseMode

fun Dispatcher.handleTorcidaCommand() {
    val torcidaMessage = """
        🔥 *FURIA NAS REDES SOCIAIS* 🔥
        
        [Twitter](https://x.com/FURIA) 🕊️
        [Instagram](https://www.instagram.com/furiagg/) 📷
        [Merch](https://adidas.furia.gg/) 👕
        [TikTok E-Sports](https://www.tiktok.com/@furiagg) 🎮
        [TikTok Creators](https://www.tiktok.com/@furia) 🫅
        [Twitch](https://www.twitch.tv/team/furia) 📺
        [YouTube](https://www.youtube.com/@FURIAggCS) 📽️
        
        *#VAIFURIA* 🔥🔥🔥
    """.trimIndent()

    command("torcida") {
        bot.sendMessage(ChatId.fromId(message.chat.id), torcidaMessage, ParseMode.MARKDOWN, replyMarkup = KeyboardMenu.createMainKeyboard())
    }

    callbackQuery("torcida") {
        val chatId = callbackQuery.message?.chat?.id ?: return@callbackQuery
        bot.sendMessage(ChatId.fromId(chatId), torcidaMessage, ParseMode.MARKDOWN, replyMarkup = KeyboardMenu.createMainKeyboard())
    }
}