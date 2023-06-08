package com.tasks.gym_dashboard.presentation

import android.util.Log

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasks.api.GymApi
import com.tasks.api.consumer.GymApiConsumer
import com.tasks.data.model.GymItem
import kotlinx.coroutines.*


class GymsViewModel (private val stateHandle: SavedStateHandle):ViewModel() {

    private   val TAG = "GymsViewModel"
    var gymsState by mutableStateOf(emptyList<GymItem>())
    var gymState by mutableStateOf(GymItem())

    val coroutineExceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG, "handleException: "+exception.message)
    }


     val gymApi: GymApi
    init {
        gymApi=GymApiConsumer().getApi()
    }


    suspend fun getListOfGyms(){
        viewModelScope.launch(coroutineExceptionHandler) {
            val list = getList()

            Log.d(TAG, "getListOfGyms: "+list.toString())
            gymsState=list
        }
    }


    suspend fun getSingleGym(){
        viewModelScope.launch(coroutineExceptionHandler) {
            val gymItem = getGym(stateHandle.get<Int>("gym_id")?:0)

            Log.d(TAG, "gymItem: "+gymItem.toString())
            gymState=gymItem.values.first()
        }
    }
    suspend  private  fun  getGym(id: Int) = withContext(Dispatchers.IO){
        gymApi.itemGym("\"id\"",id)
    }
    suspend  private  fun  getList()= withContext(Dispatchers.IO){
        gymApi.listOfGyms()
    }

}