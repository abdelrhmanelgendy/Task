package com.tasks.foreground_services_phillip

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log

class MediaPlayerService:Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return  null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        Log.d("MediaPlayerServiceTAG", "onStartCommand: ${intent?.action}")
        val foregroundUtils = ForegroundUtils()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            foregroundUtils.createNotificationChannel(this)
        }

        val notification = foregroundUtils.createNotificationBuilder(this)
        startForeground(123,notification)
        return START_STICKY

    }

}