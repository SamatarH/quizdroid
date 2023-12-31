package edu.uw.ischool.samatar.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application() {

    lateinit var topicRepository: TopicRepository
        private set

    companion object {
        lateinit var instance: QuizApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        topicRepository = JsonTopicRepository(this)
        Log.d("QuizApp", "Application is loaded and running")
    }
}



