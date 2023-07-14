package com.tasks.worker_manager

import android.app.Application
import android.app.NotificationManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.Configuration
 import com.tasks.domain.UseCaseClient

class MyApp:Application(),Configuration.Provider {
    override fun getWorkManagerConfiguration(): Configuration {
        val use =UseCaseClient()
         return Configuration.Builder()
            .setWorkerFactory(MyWorkerFactory(getSystemService(
                NOTIFICATION_SERVICE) as NotificationManager)).build()


    }



}