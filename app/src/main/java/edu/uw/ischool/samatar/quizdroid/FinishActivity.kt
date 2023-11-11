package edu.uw.ischool.samatar.quizdroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.TextView

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val totalQuestions = intent.getIntExtra("totalQuestions", 0)
        val totalCorrectAnswers = intent.getIntExtra("totalCorrectAnswers", 0)

        val scoreTextView: TextView = findViewById(R.id.scoreTextView)
        val messageTextView: TextView = findViewById(R.id.messageTextView)
        val playAgainButton: Button = findViewById(R.id.playAgainButton)

        // Calculate the percentage of correct answers
        val percentage = (totalCorrectAnswers.toDouble() / totalQuestions.toDouble() * 100).toInt()

        // Display the user's score and a message based on their performance
        scoreTextView.text = "Your Score: $totalCorrectAnswers out of $totalQuestions"
        messageTextView.text = "You answered $percentage% of questions correctly."

        // Set up click listener for the "Play Again" button
        playAgainButton.setOnClickListener {
            // Return to the MainActivity to start a new quiz
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
