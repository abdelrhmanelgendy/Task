package com.aphillip_study_jetpack_basics.shared_components

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*

class TopAppBarWithMenu : ComponentActivity() {

    val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            Log.d("TAG1155", "handleException: " + exception.message)


        }


    private  val TAG = "TopAppBarWithMenu22"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//
//        GlobalScope.launch {
//            throw Exception("Divide By Zero Exception")
//        }


        GlobalScope.launch {
            supervisorScope {
                val usersDeferred = async {
                    Log.d(TAG, "onCreate: A1")
                    delay(3000)
//                    return@async 50
                                throw Exception("Divide By Zero Exception")

                }
                val moreUsersDeferred = async {
                    Log.d(TAG, "onCreate: B1")
                    delay(6000)
//                    return@async 80

            throw Exception("Divide By Zero Exception")


                }
                val users = try {
                    usersDeferred.await()
                } catch (e: Exception) {
                    Log.d(TAG, "onCreate: A2")
                    -50
                }

                val moreUsers = try {
                    moreUsersDeferred.await()
                } catch (e: Exception) {
                    Log.d(TAG, "onCreate: B2")
                    -80

                }
                Log.d(TAG, "onCreate: single user "+users)
                Log.d(TAG, "onCreate: more user"+moreUsers)

            }
        }





        setContent {
            MenuOptions()

        }
    }

    @Composable
    fun MenuOptions() {


        val expanded = remember {
            mutableStateOf(false)
        }
        val context = LocalContext.current
        TopAppBar(title = { Text(text = "Hello Again, How Are You ?") }, actions = {
            IconButton(onClick = {
                Toast.makeText(context, "Favorite Toggled", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.Favorite, contentDescription = null)
            }
            IconButton(onClick = {
                expanded.value = true
            }) {
                Icon(Icons.Default.MoreVert, contentDescription = null)
            }
        })
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            offset = DpOffset(x = (-66).dp, y = (-10).dp)
        ) {
            DropdownMenuItem(onClick = {
                expanded.value = false
            }) {
                Text(text = "Settings")
            }
            DropdownMenuItem(onClick = {
                expanded.value = false
            }) {
                Text(text = "Logout")
            }

        }
    }
}