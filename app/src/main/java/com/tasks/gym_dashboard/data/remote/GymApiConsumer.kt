package com.tasks.gym_dashboard.api.consumer

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.tasks.gym_dashboard.data.remote.GymApi
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class GymApiConsumer {
    fun getApi(context: Context) =
        Retrofit
            .Builder()
            .baseUrl("https://fluttermsgs-default-rtdb.firebaseio.com/")
            .client(getHttpClient(context))
            .addConverterFactory(GsonConverterFactory
                .create())
            .build()
            .create(GymApi::class.java)


    fun getHttpClient(context: Context) = OkHttpClient.Builder()
//        .cache(Cache(File(context.cacheDir, "http-cache"), 10L * 1024L * 1024L))
//        .addNetworkInterceptor(ServerInterceptor())
//        .addInterceptor(LocalAppInterceptor(context))
        .build()



}

class LocalAppInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response{
        Log.d("LocalAppInterceptor", "LocalAppInterceptor intercept: request"+chain.request().url.toString())

        val builder: Request.Builder = chain.request().newBuilder()
        if (!isNetworkConnected()) {
            builder.cacheControl(CacheControl.FORCE_CACHE);
        }

        val proceed = chain.proceed(builder.build());
        Log.d("LocalAppInterceptor", "LocalAppInterceptor intercept: response"+proceed.message)

        return  proceed
    }

    private fun isNetworkConnected(): Boolean {
        val cm =context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}

class ServerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        Log.d("ServerInterceptor", "ServerInterceptor intercept: request"+chain.request().url.toString())

        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(10, TimeUnit.DAYS)
            .build()

        Log.d("ServerInterceptor", "ServerInterceptor intercept: response"+response.message)

        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}