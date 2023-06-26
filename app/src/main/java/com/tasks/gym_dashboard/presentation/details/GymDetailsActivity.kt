//package com.tasks.gym_dashboard.presentation.details
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.viewModels
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.CircularProgressIndicator
//import androidx.compose.material.Scaffold
//import androidx.compose.material.Text
//import androidx.compose.material.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.lifecycleScope
//
//private const val TAG = "MainActivityTAG"
//
//class GymDetailsActivity : ComponentActivity() {
//    lateinit var gymViewModel1: GymsViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val gymViewModel: GymsViewModel by viewModels()
//
//        gymViewModel1 = gymViewModel
//
//        intent?.let {
//            lifecycleScope.launchWhenStarted {
////                gymViewModel.getSingleGym()
//            }
//
//        }
//
//
//
//        setContent {
//            Scaffold(topBar = {
//                TopAppBar(
//                    modifier = Modifier.height(35.dp), title = {
//                        Text(text = "Top App Bar")
//                    }, backgroundColor = Color.Black, contentColor = Color.White, elevation = 10.dp
//                )
//            }, content = {
//                GymDetailsContent(gymViewModel1.gymState)
//
//
//            })
//
//
//        }
//    }
//
//
//}
//
//@Composable
//fun GymDetailsContent(gym: GymItem) {
//
//    if (gym.id == -1)
//        CircularProgressIndicator()
//    else
//        Column(
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.padding(10.dp)
//        ) {
//            Text(text = gym.id.toString())
//            Text(text = gym.gym_name ?: "")
//            Text(text = gym.gym_location ?: "")
//        }
//}
//
//
//
//
