package com.tasks.alarm_manager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.work.*
import com.tasks.navigationcomponent.R
import com.tasks.worker_manager.SimpleWorkerManager
import java.util.concurrent.TimeUnit
import kotlin.random.Random


class AlarmReceiver : BroadcastReceiver() {
    lateinit var notificationManager:NotificationManager
    override fun onReceive(context: Context, intent: Intent) {
        notificationManager=context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Is triggered when alarm goes off, i.e. receiving a system broadcast
        if (intent.action == "FOO_ACTION") {
            val fooString = intent.getStringExtra("KEY_FOO_STRING")
            Toast.makeText(context, fooString, Toast.LENGTH_LONG).show()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel()
            }
            val notification: Notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Notification.Builder(context, "CHANNEL_ID")
                    .setContentTitle("notification title")
                    .setContentText("notification text ${Random.nextInt()}")
                    .setSmallIcon(R.drawable.logo)
                     .build()
            } else {
                TODO("VERSION.SDK_INT < O")
            }

            notificationManager.notify(Random.nextInt(),notification)

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build()


            val data = Data.Builder()
                .putInt("my_age", 24)
                .build()

            val worker = WorkManager.getInstance(context)
            val request = OneTimeWorkRequest
                .Builder(SimpleWorkerManager::class.java)
                .setConstraints(constraints)
//            .setInputData(data)
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR, 1, TimeUnit.SECONDS
                ).build()
            worker.enqueue(request)
        }
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