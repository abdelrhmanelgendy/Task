package com.tasks.gym_dashboard.data.remote

import retrofit2.http.GET
import retrofit2.http.Query


interface GymApi {


    @GET("gyms.json")
    suspend fun listOfGyms():List<RemoteGym>;

    @GET("gyms.json")
    suspend fun itemGym(
        @Query("orderBy") orderingBy:String,
        @Query("equalTo") id:Int,

    ):Map<Int, RemoteGym>


}