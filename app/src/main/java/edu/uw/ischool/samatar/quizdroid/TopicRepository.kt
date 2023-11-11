package edu.uw.ischool.samatar.quizdroid

interface TopicRepository {
    fun getTopics(): List<Topic>
    fun getTopicById(topicId: Int): Topic?
}

data class Topic(
    val id: Int,
    val title: String,
    val shortDescription: String,
    val longDescription: String,
    val questions: List<Question>
)
