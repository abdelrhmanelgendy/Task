
package com.threadsapp
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.nameisjayant.threadsapp.bottom_bar.BottomBar
import com.nameisjayant.threadsapp.navigation.AppNavigation
import com.nameisjayant.threadsapp.ui.theme.ThreadsAppTheme
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            NavigationComponentTheme() {
                Scaffold(

                    bottomBar = {
                        BottomAppBar(

                            modifier = Modifier.height(60.dp),
                             contentPadding = PaddingValues(horizontal = 20.dp),
                        ) {
                            BottomBar(navHostController = navHostController)
                        }
                    }
                ) {
                    AppNavigation(navHostController)
                }
            }
        }
    }
}
