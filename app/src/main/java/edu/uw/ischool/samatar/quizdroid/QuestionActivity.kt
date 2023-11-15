package edu.uw.ischool.samatar.quizdroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import edu.uw.ischool.samatar.quizdroid.databinding.ActivityQuestionBinding
import android.widget.RadioButton
import android.widget.Toast
import edu.uw.ischool.samatar.quizdroid.Question

class QuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionBinding
    private val quizViewModel: QuizViewModel by viewModels()
    private var currentQuestionIndex: Int = 0
    private var totalQuestions: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.submitButton.setOnClickListener {
            val selectedOptionId = binding.radioGroup.checkedRadioButtonId
            if (selectedOptionId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedOptionId)
                val selectedAnswer = selectedRadioButton.text.toString()
                val correctAnswer = quizViewModel.questionsLiveData.value?.get(currentQuestionIndex)?.correctAnswer ?: ""

                quizViewModel.checkAnswer(selectedAnswer, correctAnswer)

                if (currentQuestionIndex < (quizViewModel.questionsLiveData.value?.size ?: 0) - 1) {
                    currentQuestionIndex++
                    updateQuestion()
                } else {
                    navigateToAnswerActivity(selectedAnswer, correctAnswer)
                }
            } else {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            }
        }

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
    }

    private fun updateQuestion() {
        val currentQuestion = quizViewModel.questionsLiveData.value?.get(currentQuestionIndex)
        currentQuestion?.let {
            binding.questionTextView.text = it.text
            val options = it.options
            binding.radioGroup.removeAllViews()
            for (i in options.indices) {
                val radioButton = RadioButton(this)
                radioButton.text = options[i]
                binding.radioGroup.addView(radioButton)
            }
        }
    }

    private fun navigateToAnswerActivity(selectedAnswer: String, correctAnswer: String) {
        val intent = Intent(this, AnswerActivity::class.java)
        intent.putExtra("totalQuestions", totalQuestions)
        intent.putExtra("totalCorrectAnswers", quizViewModel.totalCorrectAnswers)
        intent.putExtra("selectedAnswer", selectedAnswer)
        intent.putExtra("correctAnswer", correctAnswer)
        startActivity(intent)
    }
}
