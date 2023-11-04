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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set up Submit button click listener
        binding.submitButton.setOnClickListener {
            val selectedOptionId = binding.radioGroup.checkedRadioButtonId
            if (selectedOptionId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedOptionId)
                val selectedAnswer = selectedRadioButton.text.toString()
                val correctAnswer = quizViewModel.questionsLiveData.value?.get(currentQuestionIndex)?.correctAnswer
                quizViewModel.checkAnswer(selectedAnswer, correctAnswer!!)
                // Show the answer page after checking the answer
                navigateToAnswerActivity()
            } else {
                // If no option selected, show a Toast message
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            }
        }

        // Observe questionsLiveData and display the first question
        quizViewModel.questionsLiveData.observe(this, Observer { questions ->
            if (currentQuestionIndex < questions.size) {
                val currentQuestion = questions[currentQuestionIndex]
                binding.questionTextView.text = currentQuestion.text
                val options = currentQuestion.options
                binding.radioGroup.removeAllViews()
                for (i in options.indices) {
                    val radioButton = RadioButton(this)
                    radioButton.text = options[i]
                    binding.radioGroup.addView(radioButton)
                }
            } else {
                // No more questions, finish the quiz
                navigateToAnswerActivity()
            }
        })

        // Fetch questions based on the selected category (math, physics, marvel)
        val selectedCategory = intent.getStringExtra("CATEGORY") ?: "math"
        quizViewModel.fetchQuestions(selectedCategory)
    }

    // Function to navigate to AnswerActivity
    private fun navigateToAnswerActivity() {
        val intent = Intent(this, AnswerActivity::class.java)
        // Pass necessary data to AnswerActivity
        intent.putExtra("totalQuestions", currentQuestionIndex)
        intent.putExtra("totalCorrectAnswers", quizViewModel.totalCorrectAnswers)
        startActivity(intent)
        finish() // Finish the current activity to prevent going back to the quiz after finishing it
    }
}


