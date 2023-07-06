package com.tasks.services

import android.app.IntentService
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.annotation.CallSuper

class BackgroundService : IntentService("") {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onHandleIntent(p0: Intent?) {
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {



        Log.d(TAG, "onStartCommand: " + Thread.currentThread().name)
        return START_STICKY
    }

}

class LoginActivity :MyActivity(){
    override fun onCreate(id:Int): MyActivity {




        return super.onCreate(885)

    }
}

open class MyActivity {


    @CallSuper
    open  fun onCreate(id:Int): MyActivity {
        return this
    }

}