package com.tasks.gym_dashboard.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.tasks.gym_dashboard.presentation.dashboard.GymsViewModel
import com.tasks.navigationcomponent.ui.theme.NavigationComponentTheme
import dagger.hilt.android.AndroidEntryPoint

import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val gymsViewModel:GymsViewModel = viewModel()

            lifecycleScope.launchWhenStarted {
                gymsViewModel.getListOfGyms()
            }
            NavigationComponentTheme {

               GymsScreen(gymsScreenState =
                gymsViewModel.gymsState.value) { id, isFavorite ->
                    gymsViewModel.toggleIsFavorite(id, isFavorite)




                }
            }
        }

    }
}

@Composable
private fun GymsApp() {


}




