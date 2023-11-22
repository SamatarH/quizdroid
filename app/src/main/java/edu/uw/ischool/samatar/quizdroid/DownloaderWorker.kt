package edu.uw.ischool.samatar.quizdroid

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class DownloaderWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            // Get the URL from inputData (passed when enqueuing the work)
            val url = inputData.getString("url") ?: return@withContext Result.failure()

            // Perform the download
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()

            // Save the response to a file
            if (response.isSuccessful) {
                val fileContents = response.body?.string() ?: return@withContext Result.failure()
                applicationContext.openFileOutput("questions.json", Context.MODE_PRIVATE).use {
                    it.write(fileContents.toByteArray(Charsets.UTF_8))
                }
                Result.success()
            } else {
                Result.failure()
            }
        } catch (e: Exception) {
            // Handle the exception, retry or fail based on your logic
            Result.retry()
        }
    }
}
