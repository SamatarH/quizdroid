package edu.uw.ischool.samatar.quizdroid

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class SyncManager(private val context: Context) {
    fun scheduleDownload(url: String, interval: Long) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val downloadWorkRequest = PeriodicWorkRequestBuilder<DownloaderWorker>(interval, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(workDataOf("url" to url))
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "DownloadWork",
            ExistingPeriodicWorkPolicy.KEEP,
            downloadWorkRequest
        )
    }
}
