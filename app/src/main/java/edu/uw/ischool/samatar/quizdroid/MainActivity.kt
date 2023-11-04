package edu.uw.ischool.samatar.quizdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.content.Intent
import android.widget.AdapterView

class MainActivity : AppCompatActivity() {

    private val topics = arrayOf("Math", "Physics", "Marvel Super Heroes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.listView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, topics)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedTopic = topics[position]
            val intent = Intent(this, TopicOverviewActivity::class.java)
            intent.putExtra("TOPIC", selectedTopic)
            startActivity(intent)
        }
    }
}