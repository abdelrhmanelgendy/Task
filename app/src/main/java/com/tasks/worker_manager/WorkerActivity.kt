package com.tasks.worker_manager

import android.Manifest
import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import androidx.work.*
import java.util.concurrent.TimeUnit

class WorkerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        intent?.let {
            val intExtra = it.getIntExtra("progress", -1)
            Toast.makeText(this,"current prog $intExtra",Toast.LENGTH_SHORT).show()
        }
        setContent {
            Scaffold() {
                Column {
                    WorkerButton("Simple Worker", onClick = {
                        startSimpleWorker()
                    })
                    WorkerButton("Stop Simple Worker", onClick = {
                        stopSimpleWorker()
                    })
                }
            }
        }
    }

    private fun stopSimpleWorker() {
        WorkManager.getInstance(this).cancelAllWork()
    }


    @SuppressLint("Range")
    private fun startSimpleWorker() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS),65)
        }


        val cursor= contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null)

         while (cursor!!.moveToNext()) {
            val contactId =  cursor.getString(
                cursor.getColumnIndex(ContactsContract.Contacts.NAME_RAW_CONTACT_ID))
                val hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));


//             Log.d("TAG", "startSimpleWorker: 001 $hasPhone ")

             if (hasPhone=="1") {
                // You know it has a number so now query it like this
                val phones = contentResolver
                    .query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+
                                contactId, null, null);
                while (phones!!.moveToNext()) {
                    val phoneNumber = phones.getString(phones.getColumnIndex( ContactsContract.CommonDataKinds.Phone.PHO));
                    val name = phones.getString(phones.getColumnIndex( ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));



                    if((phoneNumber?:"").contains("182"))
                    {
                        Log.d("TAG", "startSimpleWorker: $name    "+phoneNumber)

                    }
                }
                phones.close();
            }

//            val emails = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
//                null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId, null, null);
//
//            while (emails.moveToNext()) {
//                // This would allow you get several email addresses
//                String emailAddress = emails.getString(
//                        emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
//            }
//
//            emails.close();
        }
        cursor.close()

//        val constraints = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//             .setRequiresBatteryNotLow(true)
//            .build()
//
//
//        val data = Data.Builder()
//            .putInt("my_age", 24)
//            .build()
//
//
//        val request = PeriodicWorkRequest.Builder(
//            SimpleWorkerManager::class.java,5,TimeUnit.SECONDS
//        )
//
//            .setConstraints(constraints)
//            .setInputData(data)
////            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
//            .setBackoffCriteria(
//                BackoffPolicy.LINEAR, 2, TimeUnit.HOURS
//            ).build()
//
//        WorkManager.getInstance(this).enqueue(request)
    }

    @Composable
    fun WorkerButton(txt:String,onClick:() ->Unit) {
        Button(onClick = onClick) {
            Text(text = txt)
        }
    }
}