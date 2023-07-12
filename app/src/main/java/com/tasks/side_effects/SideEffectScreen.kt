package com.tasks.side_effects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SideEffectScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()


            val counter  by produceState(initialValue = "False"){

                delay(5000)
                value="My Name Is Mostafa"
            }
            NavigationComponentTheme() {
                Scaffold(scaffoldState = scaffoldState) {


//                if(counter.length%5==0){
//                    LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
//                        scaffoldState.snackbarHostState.showSnackbar("number is divisible")
//                    }
//                }

                    TextButton(onClick = { }) {
                        Text(text = "CLick Me $counter")
                    }
                }
            }
        }
    }
}