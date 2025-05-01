package com.furia.com.furia.handler

import com.furia.com.furia.menu.KeyboardMenu
import com.furia.com.furia.service.MatchService
import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.callbackQuery
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.ParseMode

fun Dispatcher.handleLiveCommand() {
    val matchService = MatchService()

    command("live") {
        val (_, liveMessage) = matchService.isMatchLive()
        bot.sendMessage(ChatId.fromId(message.chat.id), liveMessage, ParseMode.MARKDOWN, replyMarkup = KeyboardMenu.createMainKeyboard())
    }

    callbackQuery("live") {
        val (_, liveMessage) = matchService.isMatchLive()
        val chatId = callbackQuery.message?.chat?.id ?: return@callbackQuery
        bot.sendMessage(ChatId.fromId(chatId), liveMessage, ParseMode.MARKDOWN, replyMarkup = KeyboardMenu.createMainKeyboard())
    }
}
