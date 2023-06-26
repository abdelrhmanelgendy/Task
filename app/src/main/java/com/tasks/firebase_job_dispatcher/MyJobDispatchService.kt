//package com.tasks.firebase_job_dispatcher
//
//import android.annotation.SuppressLint
//import android.util.Log
//import com.firebase.jobdispatcher.JobParameters
//import com.firebase.jobdispatcher.JobService
//
//@SuppressLint("SpecifyJobSchedulerIdRange")
//class MyJobDispatchService : JobService() {
//    private val TAG = "MyJobDispatchService"
//    override fun onStartJob(p0: JobParameters?): Boolean {
//        Log.d(TAG, "onStartJob: " + p0.toString())
//        return true
//
//    }
//
//    override fun onStopJob(p0: JobParameters?): Boolean {
//        Log.d(TAG, "onStopJob: " + p0.toString())
//        return false
//    }
//}