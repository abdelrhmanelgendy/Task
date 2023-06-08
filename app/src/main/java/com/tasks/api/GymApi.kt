package com.tasks.api

import com.tasks.data.model.GymItem
import retrofit2.http.GET
import retrofit2.http.Query


interface GymApi {


    @GET("gyms.json")
    suspend fun listOfGyms():List<GymItem>;

    @GET("gyms.json")
    suspend fun itemGym(
        @Query("orderBy") orderingBy:String,
        @Query("equalTo") id:Int,

    ):Map<Int,GymItem>


}