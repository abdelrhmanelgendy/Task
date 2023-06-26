package com.tasks.gym_dashboard.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class GymItem(
    var gym_name: String,
    var gym_location: String,
    var id: Int=-1,
    var is_open: Boolean,
    var is_Favorite: Boolean=false
)