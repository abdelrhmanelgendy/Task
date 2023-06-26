package com.tasks.gym_dashboard.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
 import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationComponentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFEF4746)
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = rememberNavController(), startDestination = "12") {
                        composable(route = "12") {
                            DashboardActivity()
                        }

                    }                }
            }
        }

    }
}

@Composable
private fun GymsApp() {


}




