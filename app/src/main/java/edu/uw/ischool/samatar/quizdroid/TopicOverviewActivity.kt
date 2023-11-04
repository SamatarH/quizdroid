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
        val topicDescriptionTextView: TextView = findViewById(R.id.topicDescriptionTextView)
        val totalQuestionsTextView: TextView = findViewById(R.id.totalQuestionsTextView)
        val beginButton: Button = findViewById(R.id.beginButton)

        topicDescriptionTextView.text = "Description for $topic"
        totalQuestionsTextView.text = "Total questions: 5"

        beginButton.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }
    }
}