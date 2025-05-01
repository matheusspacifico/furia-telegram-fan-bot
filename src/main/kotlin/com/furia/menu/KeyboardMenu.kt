package com.furia.com.furia.menu

import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton

object KeyboardMenu {
    fun createMainKeyboard(): InlineKeyboardMarkup {
        return InlineKeyboardMarkup.create(
            listOf(
                InlineKeyboardButton.CallbackData(text = "Start", callbackData = "start"),
                InlineKeyboardButton.CallbackData(text = "Upcoming", callbackData = "upcoming"),
                InlineKeyboardButton.CallbackData(text = "Live", callbackData = "live"),
                InlineKeyboardButton.CallbackData(text = "Torcida", callbackData = "torcida")
            )
        )
    }
}