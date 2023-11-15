package edu.uw.ischool.samatar.quizdroid

import android.content.Context
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import java.io.IOException

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
            jsonParser.decodeFromString(jsonString)
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }

    override fun getTopicById(topicId: Int): Topic? {
        return getTopics().find { it.id == topicId }
    }
}



