package com.tasks.navigationcomponent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tasks.navigationcomponent.data.listOfGyms

class GymViewModel:ViewModel() {
    var gymState by mutableStateOf(listOfGyms())


    fun toogleGymItem(itemId: Int){

       val gymState2=gymState.toMutableList()
        val indexOfFirst = gymState2.indexOfFirst { it.id == itemId }
        gymState2[indexOfFirst] = gymState2[indexOfFirst].copy(isFavorite
        = !gymState2[indexOfFirst].isFavorite)
        gymState=gymState2
     }
}