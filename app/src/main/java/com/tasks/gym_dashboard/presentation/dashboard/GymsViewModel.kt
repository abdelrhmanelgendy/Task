package com.tasks.gym_dashboard.presentation.dashboard

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasks.gym_dashboard.domain.GetAllSortedGymsUseCase
import com.tasks.gym_dashboard.domain.GymItem
import com.tasks.gym_dashboard.domain.ToggleFavoriteStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GymsViewModel @Inject constructor(
    private val toggleFavoriteStateUseCase: ToggleFavoriteStateUseCase,
    private val getAllSortedGymsUseCase: GetAllSortedGymsUseCase
) : ViewModel() {


    private val TAG = "GymsViewModel"
    private var _gymsState by mutableStateOf(
        GymScreenState(
            gyms = emptyList<GymItem>(),
            progress = true,
            errorMsg = ""
        )
    )


    var gymsState: State<GymScreenState> = derivedStateOf { _gymsState }


    var gymState by mutableStateOf(
        GymItem(
            gym_name = "-1",
            gym_location = "-1",
            id = -1,
            is_open = false,
        )
    )

    val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            Log.d(TAG, "handleException: " + exception.message)
            _gymsState = _gymsState.copy(errorMsg = exception.message ?: "", progress = false)

        }


    suspend fun getListOfGyms() {
        viewModelScope.launch(coroutineExceptionHandler)
        {
            val list = getAllSortedGymsUseCase()
            _gymsState = _gymsState.copy(gyms = list, progress = false)
        }
    }


    fun toggleIsFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val toggleSavedGym = toggleFavoriteStateUseCase(id, isFavorite)
            _gymsState = _gymsState.copy(gyms = toggleSavedGym)
        }
    }

}