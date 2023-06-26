package com.tasks.gym_dashboard.data.remote

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class RemoteGym(
    var gym_name: String,
    var gym_location: String,
    var id: Int=-1,
    var is_open: Boolean,
    var is_Favorite: Boolean=false
)