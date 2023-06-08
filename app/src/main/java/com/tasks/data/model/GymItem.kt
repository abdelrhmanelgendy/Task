package com.tasks.data.model

data class GymItem(
    var gym_location: String? =null,
    var gym_name: String? =null,
    var id: Int=-1,
    var is_open: Boolean? =null,
    var is_Favorite: Boolean=false
)