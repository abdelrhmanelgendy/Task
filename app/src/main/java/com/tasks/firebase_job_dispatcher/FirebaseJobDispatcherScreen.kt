//package com.tasks.firebase_job_dispatcher
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.Column
//import androidx.compose.material.Button
//import androidx.compose.material.Text
//import com.firebase.jobdispatcher.Constraint
//import com.firebase.jobdispatcher.FirebaseJobDispatcher
//import com.firebase.jobdispatcher.GooglePlayDriver
//import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme
//
//class FirebaseJobDispatcherScreen : ComponentActivity() {
//    companion object {
//        const val JOB_TAG = "this is tag"
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            val jobDispatchService = FirebaseJobDispatcher(GooglePlayDriver(this))
//            val job = jobDispatchService.newJobBuilder().addConstraint(Constraint.DEVICE_CHARGING)
//                .addConstraint(Constraint.ON_ANY_NETWORK).setTag(JOB_TAG)
//                .setService(MyJobDispatchService::class.java).build()
//            NavigationComponentTheme {
//                Column() {
//                    Button(onClick = {
//                        jobDispatchService.mustSchedule(job)
//
//                    }) {
//                        Text(text = "Start Job")
//                    }
//                    Button(onClick = {
//                        jobDispatchService.cancel(JOB_TAG)
//
//                    }) {
//                        Text(text = "End Job")
//                    }
//                }
//            }
//        }
//    }
//}