package com.tasks.gym_dashboard.presentation

import com.tasks.gym_dashboard.data.model.GymItem

data class GymScreenState(val gyms:List<GymItem>,val progress:Boolean,val errorMsg:String)
