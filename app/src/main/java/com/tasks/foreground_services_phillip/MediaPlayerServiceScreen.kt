package com.tasks.foreground_services_phillip

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text

class MediaPlayerServiceScreen: ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Button(onClick = {

                    val service =Intent(this@MediaPlayerServiceScreen,MediaPlayerService::class.java)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForegroundService(service)
                    }else{
                        startService(service)
                    }
                }) {
                    Text(text = "START")
                }
                Button(onClick = {
                    val service =Intent(this@MediaPlayerServiceScreen,MediaPlayerService::class.java)

                    stopService(service)
                }) {
                    Text(text = "STOP")
                }
            }
        }
    }
}