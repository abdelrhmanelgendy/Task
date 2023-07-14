package com.tasks.broadcast_reciever_deep_in

import android.Manifest
import android.app.LocaleManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
 import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.maps.model.LatLng
import com.tasks.broadcast_reciever_deep_in.local.MyLocalReceiver
import com.tasks.broadcast_reciever_deep_in.model_data.Person
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme

class ReceiverScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.SEND_SMS),51)
        }
        setContent {
            NavigationComponentTheme {
                Column() {
                    Button(onClick = {
                        kotlin.runCatching {



                            val intent =Intent(this@ReceiverScreen,MyLocalReceiver::class.java)
                            val persons = Person(name="Mostafa Mosad Elgendy",age=25, address = LatLng(54545.0,545.0))

                            intent.putExtra("data",persons.name)
                            LocalBroadcastManager.getInstance(this@ReceiverScreen)
                                .sendBroadcast(Intent(this@ReceiverScreen,MyLocalReceiver::class.java))

//                            sendBroadcast(
//                                intent,
//                                Manifest.permission.SEND_SMS
//                            )
                        }.onFailure {
                            Log.d("TAG", "onCreate: " + it.message)
                        }


                    }) {
                        Text(text = "LOCAL RECEIVER")
                    }
                    Button(onClick = {

                    }) {
                        Text(text = "NORMAL RECEIVER")
                    }
                }
            }
        }
    }

     override fun onPause() {
        super.onPause()
         val intent =Intent(this@ReceiverScreen,MyLocalReceiver::class.java)

        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(MyLocalReceiver())
    }
}