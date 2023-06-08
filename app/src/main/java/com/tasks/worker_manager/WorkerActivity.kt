package com.tasks.worker_manager

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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


    private fun startSimpleWorker() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
             .setRequiresBatteryNotLow(true)
            .build()


        val data = Data.Builder()
            .putInt("my_age", 24)
            .build()


        val request = PeriodicWorkRequest.Builder(
            SimpleWorkerManager::class.java,5,TimeUnit.MINUTES
        )

            .setConstraints(constraints)
            .setInputData(data)
//            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR, 2, TimeUnit.HOURS
            ).build()

        WorkManager.getInstance(this).enqueue(request)
    }

    @Composable
    fun WorkerButton(txt:String,onClick:() ->Unit) {
        Button(onClick = onClick) {
            Text(text = txt)
        }
    }
}