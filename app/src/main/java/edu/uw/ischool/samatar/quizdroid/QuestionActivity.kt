package edu.uw.ischool.samatar.quizdroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import edu.uw.ischool.samatar.quizdroid.databinding.ActivityQuestionBinding
import android.widget.RadioButton
import android.widget.Toast

class QuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionBinding
    private val quizViewModel: QuizViewModel by viewModels()
    private var currentQuestionIndex: Int = 0
    private var totalQuestions: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedCategory = intent.getStringExtra("CATEGORY") ?: "math"
        quizViewModel.fetchQuestions(selectedCategory)

        quizViewModel.questionsLiveData.observe(this, Observer { questions ->
            if (questions.isNotEmpty()) {
                totalQuestions = questions.size
                updateQuestion()
            } else {
                Toast.makeText(this, "No questions available for this category", Toast.LENGTH_SHORT).show()
                finish()
            }
        })

        binding.submitButton.setOnClickListener {
            val selectedOptionId = binding.radioGroup.checkedRadioButtonId
            if (selectedOptionId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedOptionId)
                val selectedAnswer = selectedRadioButton.text.toString()
                val correctAnswer = quizViewModel.questionsLiveData.value?.get(currentQuestionIndex)?.correctAnswer ?: ""

                quizViewModel.checkAnswer(selectedAnswer, correctAnswer)

                if (currentQuestionIndex < totalQuestions - 1) {
                    currentQuestionIndex++
                    updateQuestion()
                } else {
                    navigateToAnswerActivity(selectedAnswer, correctAnswer)
                }
            } else {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateQuestion() {
        val currentQuestion = quizViewModel.questionsLiveData.value?.get(currentQuestionIndex)
        currentQuestion?.let { question ->
            binding.questionTextView.text = question.text
            binding.radioGroup.removeAllViews()
            question.options.forEach { option ->
                val radioButton = RadioButton(this).apply {
                    text = option
                }
                binding.radioGroup.addView(radioButton)
            }
        }
    }

    private fun navigateToAnswerActivity(selectedAnswer: String, correctAnswer: String) {
        val intent = Intent(this, AnswerActivity::class.java).apply {
            putExtra("totalQuestions", totalQuestions)
            putExtra("totalCorrectAnswers", quizViewModel.totalCorrectAnswers)
            putExtra("selectedAnswer", selectedAnswer)
            putExtra("correctAnswer", correctAnswer)
        }
        startActivity(intent)
    }
}


