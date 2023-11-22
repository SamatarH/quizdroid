package edu.uw.ischool.samatar.quizdroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.uw.ischool.samatar.quizdroid.databinding.ActivityAnswersBinding
import android.view.View
import android.widget.Toast

class AnswerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnswersBinding
    private var totalQuestions: Int = 0
    private lateinit var syncManager: SyncManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        syncManager = SyncManager(this)

        totalQuestions = intent.getIntExtra("totalQuestions", 0)
        val totalCorrectAnswers = intent.getIntExtra("totalCorrectAnswers", 0)
        val selectedAnswer = intent.getStringExtra("selectedAnswer") ?: ""
        val correctAnswer = intent.getStringExtra("correctAnswer") ?: ""

        binding.userAnswerTextView.text = "Your Answer: $selectedAnswer"
        binding.correctAnswerTextView.text = "Correct Answer: $correctAnswer"
        binding.scoreTextView.text = "You have $totalCorrectAnswers out of $totalQuestions correct"

        binding.nextButton.visibility = View.GONE
        binding.finishButton.setOnClickListener {
            navigateToFirstTopicListPage()
            scheduleWork()
        }
    }

    private fun navigateToFirstTopicListPage() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    private fun scheduleWork() {
        val url = "http://example.com/questions.json" // Replace with your actual URL
        val interval = 15L // Replace with your desired interval in minutes

        Toast.makeText(this, "Scheduling download from $url", Toast.LENGTH_SHORT).show()
        syncManager.scheduleDownload(url, interval)
    }
}










