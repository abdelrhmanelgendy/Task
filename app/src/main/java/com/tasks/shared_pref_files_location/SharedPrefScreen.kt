package com.tasks.shared_pref_files_location

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.core.content.edit
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme

class SharedPrefScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val defaultSharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this@SharedPrefScreen)
        setContent {
            NavigationComponentTheme {
                Column() {
                    Button(onClick = {
                        defaultSharedPreferences.edit().putString("KEY01", "mostafa mosad elgendy")
                    }) {
                        Text(text = "DEFAULT PRIVATE FILE")
                    }
                    Button(onClick = {

                        getSharedPreferences("settings", MODE_PRIVATE).edit {
                            putString("KEY02","abdelrhman")
                        }
                    }) {
                        Text(text = "CUSTOME PRIVATE FILE")
                    }

                    Button(onClick = {

                        getSharedPreferences("settings", MODE_MULTI_PROCESS ).edit {
                            putString("KEY03","ALI")
                        }
                    }) {
                        Text(text = "CUSTOME   MODE_MULTI_PROCESS")
                    }
                }
            }
        }
    }
}