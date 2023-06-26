package com.tasks.gym_dashboard

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GymsApplication : Application() {


init {
    gymsApplication =this
}
companion object{
  private  lateinit var gymsApplication: GymsApplication
    fun getContext(): Context = gymsApplication.applicationContext
}
}