package edu.uw.ischool.samatar.quizdroid

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class QuizViewModel(application: Application) : AndroidViewModel(application) {
    private val _questionsLiveData = MutableLiveData<List<Question>>()
    val questionsLiveData: LiveData<List<Question>> get() = _questionsLiveData

    private var _totalCorrectAnswers = 0
    val totalCorrectAnswers: Int
        get() = _totalCorrectAnswers

    private val topicRepository = JsonTopicRepository(application)

    fun fetchQuestions(category: String) {
        val topics = topicRepository.getTopics()
        val selectedTopic = topics.find { it.title.equals(category, ignoreCase = true) }
        _questionsLiveData.value = selectedTopic?.questions
    }

    fun checkAnswer(userAnswer: String, correctAnswer: String) {
        if (userAnswer == correctAnswer) {
            _totalCorrectAnswers++
        }
    }

    fun startDataSync() {
        val syncManager = SyncManager(getApplication())
        val url = "http://example.com/questions.json" // Replace with your actual URL
        val interval = 15L // Replace with your desired interval in minutes
        syncManager.scheduleDownload(url, interval)
    }

}
