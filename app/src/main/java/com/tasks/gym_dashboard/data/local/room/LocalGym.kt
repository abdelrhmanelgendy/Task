package com.tasks.gym_dashboard.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Gyms_table")
data class LocalGym(

    var gym_name: String,
    var gym_location: String,
    @PrimaryKey
    @ColumnInfo(name = "gym_id")
    var id: Int=-1,
    var is_open: Boolean,
    @ColumnInfo(name = "is_favorite")
    var is_Favorite: Boolean=false
)


@Entity()
data class GymStateItem(

     @ColumnInfo(name = "gym_id")
    var id: Int=-1,
    @ColumnInfo(name = "is_favorite")
    var is_Favorite: Boolean=false
)