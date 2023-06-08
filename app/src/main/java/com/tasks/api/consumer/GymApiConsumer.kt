package com.tasks.api.consumer

import android.util.Log
import com.tasks.api.GymApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymApiConsumer {
    fun getApi() = Retrofit.Builder().baseUrl("https://fluttermsgs-default-rtdb.firebaseio.com/")
        .client(getHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(GymApi::class.java)
fun getHttpClient()=OkHttpClient.Builder().addInterceptor(getInterceptor()).build()
fun getInterceptor()=Interceptor{
    Log.d("TAG", "intercept request: "+it.request().url.toString())
    val response = it.proceed(it.request())
    Log.d("TAG", "intercept response: ")
    Log.d("TAG", "intercept response: "+response.body.toString())

    response
}

}