package edu.uw.ischool.samatar.quizdroid

import android.content.Context
import java.io.IOException

class TopicRepository(private val context: Context) {

    private val questionsFileName = "questions.json"

    fun getTopics(): List<Topic> {
        return try {
            val fis = context.openFileInput(questionsFileName)
            val json = fis.bufferedReader().use { it.readText() }
            parseTopics(json)
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }

    private fun parseTopics(json: String): List<Topic> {

    }
}
