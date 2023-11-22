package edu.uw.ischool.samatar.quizdroid

import android.content.Context
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.Serializable
import java.io.IOException

// Ensure your Topic class is in the same file or imported if it's in a different file
@Serializable
data class Topic(
    val id: Int, // I'm assuming you have an ID field based on your getTopicById function
    val title: String,
    val desc: String,
    val questions: List<Question>
)

// Ensure your Question class is also annotated with @Serializable
@Serializable
data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswer: String
)

interface TopicRepository {
    fun getTopics(): List<Topic>
    fun getTopicById(topicId: Int): Topic?
}

class JsonTopicRepository(private val context: Context) : TopicRepository {
    private val jsonFileName = "questions.json"
    private val jsonParser = Json { ignoreUnknownKeys = true }

    override fun getTopics(): List<Topic> {
        return try {
            val jsonString = context.openFileInput(jsonFileName).bufferedReader().use { it.readText() }
            jsonParser.decodeFromString<List<Topic>>(jsonString)
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }

    override fun getTopicById(topicId: Int): Topic? {
        return getTopics().find { it.id == topicId }
    }
}




