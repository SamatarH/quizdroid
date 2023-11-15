package edu.uw.ischool.samatar.quizdroid

import android.content.Context
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.IOException
import edu.uw.ischool.samatar.quizdroid.Question

@Serializable
data class Topic(
    val title: String,
    val desc: String,
    val questions: List<Question>
)

class TopicRepository(private val context: Context) {

    private val questionsFileName = "questions.json"

    fun getTopics(): List<Topic> {
        return try {
            val fis = context.openFileInput(questionsFileName)
            val json = fis.bufferedReader().use { it.readText() }
            parseTopics(json)
        } catch (e: IOException) {  // Make sure the catch block is catching IOException
            e.printStackTrace()
            emptyList()
        }
    }

    private fun parseTopics(json: String): List<Topic> {
        val jsonParser = Json { ignoreUnknownKeys = true }
        return jsonParser.decodeFromString(json)
    }
}


