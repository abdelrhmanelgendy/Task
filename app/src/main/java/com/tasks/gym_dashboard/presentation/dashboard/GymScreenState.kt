package com.tasks.gym_dashboard.presentation.dashboard

import com.tasks.gym_dashboard.domain.GymItem


data class GymScreenState(val gyms:List<GymItem>, val progress:Boolean, val errorMsg:String)
