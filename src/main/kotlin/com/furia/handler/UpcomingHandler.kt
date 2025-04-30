package com.furia.com.furia.handler

import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.callbackQuery
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

fun Dispatcher.handleUpcomingCommand() {
    val client = OkHttpClient()

    fun fetchUpcomingMatches(): String {
        val request = Request.Builder()
            .url("https://hltv-api.vercel.app/api/matches.json")
            .build()

        return try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    return "Erro na requisição da API (${response.code()})"
                }

                val body = response.body()?.string() ?: return "Resposta vazia!"
                body.take(100)
            }
        } catch (e: IOException) {
            "Erro ao buscar partidas: ${e.message}"
        }
    }

    command("upcoming") {
        val data = fetchUpcomingMatches()
        bot.sendMessage(ChatId.fromId(message.chat.id), data)
    }

    callbackQuery("upcoming") {
        val data = fetchUpcomingMatches()
        val chatId = callbackQuery.message?.chat?.id ?: return@callbackQuery
        bot.sendMessage(ChatId.fromId(chatId), data)
    }
}