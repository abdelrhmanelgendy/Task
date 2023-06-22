package com.tasks.gym_dashboard.data

import android.app.Application
import android.content.Context

class GymsApplication : Application() {


init {
    gymsApplication=this
}
companion object{
  private  lateinit var gymsApplication: GymsApplication
    fun getContext(): Context = gymsApplication.applicationContext
}
}