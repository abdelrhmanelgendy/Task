package com.tasks.broadcast_reciever_deep_in.local

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.tasks.broadcast_reciever_deep_in.model_data.Person

class MyLocalReceiver:BroadcastReceiver() {

    override fun onReceive(p0: Context?, intent: Intent?) {

        val data = intent?.getStringExtra("data")?:"-1"

        println(data)



    }
}