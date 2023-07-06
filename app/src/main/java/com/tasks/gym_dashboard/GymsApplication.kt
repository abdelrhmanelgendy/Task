package com.tasks.gym_dashboard

import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.tasks.broadcast_reciever_deep_in.AfterRebootReceiver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GymsApplication : Application() {


    init {
        gymsApplication = this
    }

    override fun onCreate() {
        super.onCreate()
         registerReceiver(AfterRebootReceiver(), IntentFilter(Intent.ACTION_BOOT_COMPLETED))

    }
    companion object {
        private lateinit var gymsApplication: GymsApplication
        fun getContext(): Context = gymsApplication.applicationContext
    }
}