package edu.uw.ischool.samatar.quizdroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.uw.ischool.samatar.quizdroid.databinding.ActivityAnswersBinding
import android.view.View

class AnswerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnswersBinding
    private var currentQuestionIndex: Int = 0
    private var totalQuestions: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswersBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        totalQuestions = intent.getIntExtra("totalQuestions", 0)
        val totalCorrectAnswers = intent.getIntExtra("totalCorrectAnswers", 0)
        val selectedAnswer = intent.getStringExtra("selectedAnswer") ?: ""
        val correctAnswer = intent.getStringExtra("correctAnswer") ?: ""

        binding.userAnswerTextView.text = "Your Answer: $selectedAnswer"
        binding.correctAnswerTextView.text = "Correct Answer: $correctAnswer"
        binding.scoreTextView.text = "You have $totalCorrectAnswers out of $totalQuestions correct"

        if (currentQuestionIndex < totalQuestions - 1) {
            binding.nextButton.setOnClickListener {
                moveToNextQuestion()
            }
            binding.finishButton.visibility = View.GONE
        } else {
            binding.nextButton.visibility = View.GONE
            binding.finishButton.setOnClickListener {
                navigateToFirstTopicListPage()
            }
        }
    }

    private fun moveToNextQuestion() {
        val intent = Intent(this, QuestionActivity::class.java)
        intent.putExtra("CATEGORY", "math") // Replace "math" with the actual category of the quiz
        startActivity(intent)
        finish()
    }

    private fun navigateToFirstTopicListPage() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}










