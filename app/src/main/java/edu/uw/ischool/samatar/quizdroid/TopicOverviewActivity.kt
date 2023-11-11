package edu.uw.ischool.samatar.quizdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.TextView
import android.widget.Button

class TopicOverviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        val topic = intent.getStringExtra("TOPIC")

        // Map topic names from TopicOverviewActivity to QuizRepository format
        val category = when (topic) {
            "Math" -> "math"
            "Physics" -> "physics"
            "Marvel Super Heroes" -> "marvel"
            else -> "" // Handle unknown topics appropriately
        }

        val topicDescriptionTextView: TextView = findViewById(R.id.topicDescriptionTextView)
        val totalQuestionsTextView: TextView = findViewById(R.id.totalQuestionsTextView)
        val beginButton: Button = findViewById(R.id.beginButton)

        // Update the topic description and total questions text
        topicDescriptionTextView.text = "Description for $topic"
        totalQuestionsTextView.text = "Total questions: 5" // You can update this dynamically based on your questions list

        // Set up click listener for the Begin button
        beginButton.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            // Pass the mapped topic to the QuestionActivity
            intent.putExtra("CATEGORY", category)
            startActivity(intent)
        }
    }
}
