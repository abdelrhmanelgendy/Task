package com.tasks.broadcast_reciever_deep_in

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.tasks.navigationcomponent.R

class AfterRebootReceiver:BroadcastReceiver() {


    lateinit var notificationManager:NotificationManager
    override fun onReceive(p0: Context?, intent: Intent?) {


        Toast.makeText(p0,"AIR PLANE ",Toast.LENGTH_LONG).show()
        notificationManager=p0?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        if(intent?.action.equals(Intent.ACTION_BOOT_COMPLETED))
        {
            val notification: Notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Notification.Builder(p0, "CHANNEL_ID")
                    .setContentTitle("notification title")
                    .setContentText("notification text 100")
                    .setSmallIcon(R.drawable.logo)
                     .build()

            } else {
                TODO("VERSION.SDK_INT < O")
            }




            Toast.makeText(p0,"BOOT COMPLETE",Toast.LENGTH_LONG).show()
            notificationManager.notify(112,notification)

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            "CHANNEL_ID",
            "channel name",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        notificationManager.createNotificationChannel(channel)
    }
}