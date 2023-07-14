package com.aphillip_study_jetpack_basics

import android.os.Bundle
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aphillip_study_jetpack_basics.shared_components.SelectedItems
import com.aphillip_study_jetpack_basics.shared_components.SplashAnimationScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme

class PhillipEntryPoint : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(
                color = Color.Black

            )

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
}