package edu.uw.ischool.samatar.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AnswerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answers)

        val userAnswer = intent.getStringExtra("userAnswer") ?: ""
        val correctAnswer = intent.getStringExtra("correctAnswer") ?: ""
        val totalCorrectAnswers = intent.getIntExtra("totalCorrectAnswers", 0)
        val totalQuestions = intent.getIntExtra("totalQuestions", 0)
        val currentQuestionIndex = intent.getIntExtra("currentQuestionIndex", 0)

        val userAnswerTextView: TextView = findViewById(R.id.userAnswerTextView)
        val correctAnswerTextView: TextView = findViewById(R.id.correctAnswerTextView)
        val scoreTextView: TextView = findViewById(R.id.scoreTextView)
        val nextButton: Button = findViewById(R.id.nextButton)

        userAnswerTextView.text = "Your Answer: $userAnswer"
        correctAnswerTextView.text = "Correct Answer: $correctAnswer"

        // Calculate and display the score (total correct vs total questions)
        val score = "You have $totalCorrectAnswers out of $totalQuestions correct"
        scoreTextView.text = score

        // Set click listener for Next button
        nextButton.setOnClickListener {
            val intent = if (currentQuestionIndex < totalQuestions - 1) {
                // Navigate to the next question
                Intent(this, QuestionActivity::class.java)
                    .putExtra("currentQuestionIndex", currentQuestionIndex + 1)
            } else {
                // If there are no more questions, navigate to FinishActivity
                Intent(this, FinishActivity::class.java)
            }
            startActivity(intent)
            finish()
        }
    }
}


