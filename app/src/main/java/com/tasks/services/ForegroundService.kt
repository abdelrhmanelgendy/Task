package com.tasks.services

import android.app.*
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.ForegroundInfo
import com.tasks.navigationcomponent.R
import com.tasks.worker_manager.WorkerActivity
import kotlin.random.Random

class ForegroundService: Service() {
    override fun onBind(p0: Intent?): IBinder? {
       return null
    }
    lateinit var notificationManager:NotificationManager

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        notificationManager=getSystemService(NOTIFICATION_SERVICE)as NotificationManager

        startForeground(101,createForegroundInfo("5005"))
        Log.d(TAG, "onStartCommand: "+Thread.currentThread().name)
        return START_STICKY
    }


    fun createForegroundInfo(progress: String): Notification {
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
        return notification
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