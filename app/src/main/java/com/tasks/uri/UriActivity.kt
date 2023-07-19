package com.tasks.uri

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme
import java.io.File
import java.io.FileOutputStream

class UriActivity : ComponentActivity() {

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContent {


            val uri = Uri.parse("android.resource://$packageName/drawable/download")

            val byteArray = contentResolver.openInputStream(uri)?.use {
                it.readBytes()
            }

            var byteArrayState = remember() {
                mutableStateOf(byteArray)
            }
            val decodeByteArray = BitmapFactory.decodeByteArray(byteArrayState.value, 0, byteArray!!.size)

            val file = File(filesDir,"image.jpg")

            FileOutputStream(file).use {
                it.write(byteArray)
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent(),
                onResult ={contentUri->
                    val bytes = contentResolver.openInputStream(contentUri!!)?.use {
                        it.readBytes()
                    }
                    byteArrayState.value=bytes
                }
            )
            println("File Uri size ${file.toURI()}")
            NavigationComponentTheme {
                Scaffold(topBar = { AppBar() }) {
                    Surface(color = Color.Black) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column {
                                Image(
                                    bitmap = decodeByteArray.asImageBitmap(),
                                    contentDescription = null
                                )
                                Button(onClick = {
                                    launcher.launch("image")
                                }) {
                                    Text(text = "Pick new Image")
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    @Composable
    fun AppBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .height(40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Uri Working")
            Icon(Icons.Default.Menu, contentDescription = null)
        }
    }

}