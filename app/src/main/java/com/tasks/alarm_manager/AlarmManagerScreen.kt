package com.tasks.alarm_manager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme
import com.tasks.services.BackgroundService
import com.tasks.services.ForegroundService

class AlarmManagerScreen: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationComponentTheme {
                Column() {
                    Button(onClick = {
                        startAlarm()
                    }) {
                        Text(text = "START")
                    }
                    Button(onClick = {
                        stopAlarm()
                    }) {
                        Text(text = "Stop")
                    }
                }
            }
        }
    }

    private fun stopAlarm() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(Intent(this,ForegroundService::class.java))
        }else
        {
            startService(Intent(this,ForegroundService::class.java))
        }
    }

    private fun startAlarm()
    {


        startService(Intent(this,BackgroundService::class.java))

//
//        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//
//// Intent part
//        val intent = Intent(this, AlarmReceiver::class.java)
//        intent.action = "FOO_ACTION"
//        intent.putExtra("KEY_FOO_STRING", "Medium AlarmManager Demo")
//
//        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//
//// Alarm time
//        val ALARM_DELAY_IN_SECOND = 1
//        val alarmTimeAtUTC = System.currentTimeMillis() + ALARM_DELAY_IN_SECOND * 1_000L
//
//// Set with system Alarm Service
//// Other possible functions: setExact() / setRepeating() / setWindow(), etc
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////
//            //
//            //
//            //
//            //
//            //            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTimeAtUTC, pendingIntent)
//
//
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                alarmTimeAtUTC,60, pendingIntent)
//
//
//
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                alarmTimeAtUTC,60, pendingIntent)


    }
}