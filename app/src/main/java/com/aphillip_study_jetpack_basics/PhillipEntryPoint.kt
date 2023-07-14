package com.aphillip_study_jetpack_basics

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aphillip_study_jetpack_basics.shared_components.SelectedItems
import com.aphillip_study_jetpack_basics.shared_components.SplashAnimationScreen
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme

class PhillipEntryPoint : ComponentActivity() {

    private val screenshotReceiver = ScreenshotReceiver()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()


        setContent {
//            val systemUiController = rememberSystemUiController()
//            systemUiController.setStatusBarColor(
//                color = Color.Black
//
//            )

            val state = rememberScaffoldState()
            NavigationComponentTheme {


                Surface(color = Color.Black) {
                    SelectedItems()
                }
//                Navigation()


//                Scaffold(scaffoldState = state) {
//                    Surface( color = Color.Black) {
////                        DropDown3DAnim(this)
////                        ProgressAnimationScreen()
//////                        ConstraintLayoutScreen()
////                    }
//                }
            }
        }
    }

    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "splashScreen") {
            composable("splashScreen") {
                SplashAnimationScreen(navController = navController)
            }
            composable("mainScreen") {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ) {
                    Text(
                        text = "this is main screen",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }

override fun onResume() {
    super.onResume()
    val intentFilter = IntentFilter(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
    registerReceiver(screenshotReceiver, intentFilter)
}

override fun onDestroy() {
    super.onDestroy()
    unregisterReceiver(screenshotReceiver)
}


    //create a function to request permission
    private fun requestPermission() { //create a list of permissions to be asked
        val permissionToBeAsked = mutableListOf<String>()
        //check if permission is granted
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //add the permission to the list of permissions to be asked
            permissionToBeAsked.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED

        ) {
            //add the permission to the list of permissions to be asked
            permissionToBeAsked.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (permissionToBeAsked.isNotEmpty()) {
            //convert the list to an array
            val permissionArray = permissionToBeAsked.toTypedArray()
            //ask for permission

            ActivityCompat.requestPermissions(this, permissionArray, 0)
        }
    }







}

class ScreenshotReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_MEDIA_SCANNER_SCAN_FILE) {
            val path = intent.data?.path ?: return
            if (path.endsWith(".png") || path.endsWith(".jpg")) {
                Log.d("TAG1122", "onReceive: $path")
            }
        }
    }
}