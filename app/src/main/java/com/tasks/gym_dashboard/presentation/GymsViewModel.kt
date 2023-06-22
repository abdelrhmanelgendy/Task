package com.tasks.gym_dashboard.presentation

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasks.gym_dashboard.data.model.GymItem
import com.tasks.gym_dashboard.data.model.GymStateItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GymsViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {

    private val TAG = "GymsViewModel"
   private var _gymsState by
    mutableStateOf(GymScreenState(gyms = emptyList<GymItem>(),progress = true, errorMsg = ""))


    var gymsState:State<GymScreenState> = derivedStateOf { _gymsState }
     val repository = Repository()


    var gymState by mutableStateOf(
        GymItem(
            gym_name = "-1",
            gym_location = "-1",
            id = -1,
            is_open = false,
            is_Favorite = false
        )
    )

    val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            Log.d(TAG, "handleException: " + exception.message)
            _gymsState = _gymsState.copy(errorMsg =exception.message?:"", progress = false)

        }





    suspend fun getListOfGyms() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val list = repository.getList()

            Log.d(TAG, "getListOfGyms: " + list.toString())
            _gymsState = _gymsState.copy(gyms = list,progress = false)
        }
    }


    suspend fun getSingleGym() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val gymItem =repository.getGym(stateHandle.get<Int>("gym_id") ?: 0)

            Log.d(TAG, "gymItem: " + gymItem.toString())
            gymState = gymItem.values.first()
        }
    }






    fun toggleIsFavorite(item: GymItem) {

        val gyms = _gymsState.gyms.toMutableList()
        val indexOfFirst = gyms.indexOfFirst {
            val b = it.id == item.id
            b
        }
        gyms[indexOfFirst] = gyms[indexOfFirst].copy(is_Favorite = !gyms[indexOfFirst].is_Favorite)
        val gymStateItem = GymStateItem(item.id, gyms[indexOfFirst].is_Favorite)
        viewModelScope.launch(Dispatchers.IO) {
            repository.gymDao.update(gymStateItem)
        }
        _gymsState =_gymsState.copy(gyms = gyms,progress = false)
    }

}