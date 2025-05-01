package com.furia.com.furia.service

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.io.IOException
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class MatchService {
    private val client = OkHttpClient()

    fun fetchUpcomingMatches(): List<Map<String, Any>> {
        val request = Request.Builder()
            .url("https://hltv-api.vercel.app/api/matches.json")
            .build()

        return try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    return emptyList()
                }

                val body = response.body()?.string() ?: return emptyList()
                val matches = JSONArray(body)
                val furiaMatches = mutableListOf<Map<String, Any>>()

                for (i in 0 until matches.length()) {
                    val match = matches.getJSONObject(i)
                    val teams = match.getJSONArray("teams")

                    for (j in 0 until teams.length()) {
                        val team = teams.getJSONObject(j)
                        val teamName = team.getString("name")

                        if (teamName == "FURIA" || teamName == "FURIA Academy") {
                            val event = match.getJSONObject("event").getString("name")
                            val time = match.getString("time")
                            val dateTime = ZonedDateTime.parse(time)
                            val formattedTime = dateTime.withZoneSameInstant(ZoneId.of("America/Sao_Paulo")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm 'GMT-3'"))

                            val opponent = if (teams.getJSONObject(0).getString("name") == "FURIA" || teams.getJSONObject(0).getString("name") == "FURIA Academy") {
                                teams.getJSONObject(1).getString("name")
                            } else {
                                teams.getJSONObject(0).getString("name")
                            }

                            val furiaType = if (teamName == "FURIA") "FURIA" else "FURIA Academy"
                            val emoji = if (teamName == "FURIA") "🦁" else "🐾"

                            furiaMatches.add(
                                mapOf(
                                    "furiaType" to furiaType,
                                    "emoji" to emoji,
                                    "event" to event,
                                    "opponent" to opponent,
                                    "time" to time,
                                    "formattedTime" to formattedTime,
                                    "dateTime" to dateTime
                                )
                            )
                            break
                        }
                    }
                }

                furiaMatches
            }
        } catch (e: IOException) {
            emptyList()
        }
    }

    fun isMatchLive(): Pair<Boolean, String> {
        val matches = fetchUpcomingMatches()
        if (matches.isEmpty()) {
            return Pair(false, "😿 Nenhuma partida da FURIA ou do Academy encontrada.\nFique ligado, em breve tem mais emoção! #VAIFURIA 🔥")
        }

        val now = ZonedDateTime.now(ZoneId.of("UTC"))

        val liveMatch = matches.find { match ->
            val matchTime = match["dateTime"] as ZonedDateTime
            val timeDifference = java.time.Duration.between(matchTime, now).abs()
            timeDifference.toHours() < 3
        }

        return if (liveMatch != null) {
            val message = """
                🔴 *${liveMatch["emoji"]} ${liveMatch["furiaType"]} ESTÁ EM JOGO AGORA!*
                *Evento:* ${liveMatch["event"]}
                *Adversário:* ${liveMatch["opponent"]}
                *Horário:* ${liveMatch["formattedTime"]}

                *Vamos torcer juntos! #VAIFURIA* 🔥🔥🔥
            """.trimIndent()
            Pair(true, message)
        } else {
            val nextMatch = matches.minByOrNull { it["dateTime"] as ZonedDateTime }
            if (nextMatch != null) {
                val message = """
                    😴 *Nenhuma partida da FURIA acontecendo agora.*

                    A próxima partida será:
                    ${nextMatch["emoji"]} *${nextMatch["furiaType"]}*
                    *Evento:* ${nextMatch["event"]}
                    *Adversário:* ${nextMatch["opponent"]}
                    *Data:* ${nextMatch["formattedTime"]}

                    *Não perca! #VAIFURIA* 🔥
                """.trimIndent()
                Pair(false, message)
            } else {
                Pair(false, "😿 Nenhuma partida da FURIA ou do Academy encontrada.\nFique ligado, em breve tem mais emoção! #VAIFURIA 🔥")
            }
        }
    }
}
