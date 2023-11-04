package edu.uw.ischool.samatar.quizdroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    private val _questionsLiveData = MutableLiveData<List<Question>>()
    val questionsLiveData: LiveData<List<Question>> get() = _questionsLiveData

    private var _totalCorrectAnswers = 0
    val totalCorrectAnswers: Int
        get() = _totalCorrectAnswers

    // Function to fetch questions based on the selected category
    fun fetchQuestions(category: String) {
        val fetchedQuestions = QuizRepository().getQuestions(category)
        _questionsLiveData.value = fetchedQuestions
    }

    // Function to handle user's answer and update correct answers count
    fun checkAnswer(userAnswer: String, correctAnswer: String) {
        if (userAnswer == correctAnswer) {
            handleCorrectAnswer()
        }
        // Any other logic related to correct answers can be added here
    }

    // Function to handle correct answer logic (incrementing correct answers count)
    fun handleCorrectAnswer() {
        _totalCorrectAnswers++
    }
}