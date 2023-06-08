package com.tasks.worker_manager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.*
import com.tasks.navigationcomponent.R


class SimpleWorkerManager(
    val context: Context,

    private val notificationManager: NotificationManager,

    workerParams: WorkerParameters,
) : Worker(context, workerParams) {
    private val TAG = "SimpleWorkerManagerTAG"
    override fun doWork(): Result {

        val my_age = inputData.getInt("my_age", -1)

        Log.d(TAG, "Job Started ${this.hashCode()} ")

        for (i in 0..1) {
            setForegroundAsync(createForegroundInfo(i))
            Thread.sleep(1000)

        }
        Log.d(
            TAG,
            "Job Finished ${Thread.currentThread().name} ${this.hashCode()} data ${my_age} ${runAttemptCount}"
        )
        return Result.success()
    }

    fun createForegroundInfo(progress: Int): ForegroundInfo {
        val intent = Intent(applicationContext, WorkerActivity::class.java)
        intent.putExtra("progress",progress)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }
        val notification: Notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(applicationContext, "CHANNEL_ID")
                .setContentTitle("notification title")
                .setContentText("notification text $progress")
                .setSmallIcon(R.drawable.logo)
                .setContentIntent(pendingIntent)
                .build()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return ForegroundInfo(123, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            "CHANNEL_ID",
            "channel name",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        notificationManager.createNotificationChannel(channel)
    }


}


class MyWorkerFactory(

    private val notificationManager: NotificationManager,


    ) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        Log.d("createWorkerTAG", "createWorker: " + workerClassName)
        Log.d("createWorkerTAG", "createWorker: " + SimpleWorkerManager::javaClass.name)

        return SimpleWorkerManager(appContext, notificationManager, workerParameters);

    }

}

class Student(val name: String) {}