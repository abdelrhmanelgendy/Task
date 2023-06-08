package com.tasks.worker_manager

import android.app.Application
import android.app.NotificationManager
import androidx.work.Configuration

class MyApp:Application(),Configuration.Provider {
    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(MyWorkerFactory(getSystemService(
                NOTIFICATION_SERVICE) as NotificationManager)).build()
    }


}