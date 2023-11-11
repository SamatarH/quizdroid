package edu.uw.ischool.samatar.quizdroid

class InMemoryTopicRepository : TopicRepository {
    private val topicsList: List<Topic> by lazy {
        listOf(
            Topic(
                1,
                "Math",
                "Short description for Math",
                "Long description for Math",
                listOf(
                    Question("What is 2 + 2?", listOf("3", "4", "5", "6"), "4"),
                    Question("What is 3 x 7?", listOf("18", "21", "24", "27"), "21")
                )
            ),
        )
    }

    override fun getTopics(): List<Topic> {
        return topicsList
    }

    override fun getTopicById(topicId: Int): Topic? {
        return topicsList.find { it.id == topicId }
    }
}



