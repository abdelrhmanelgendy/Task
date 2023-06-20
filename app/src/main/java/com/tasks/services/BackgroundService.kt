package com.tasks.services

import android.app.IntentService
import android.app.Service
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.IBinder
import android.util.Log

class BackgroundService: IntentService("") {
    override fun onBind(p0: Intent?): IBinder? {
       return null
    }

    override fun onHandleIntent(p0: Intent?) {
     }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d(TAG, "onStartCommand: "+Thread.currentThread().name)
        return START_STICKY
    }

}