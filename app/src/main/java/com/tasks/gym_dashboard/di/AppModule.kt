package com.tasks.gym_dashboard.di

import android.content.Context
import androidx.room.Room
import com.tasks.gym_dashboard.data.Repository
import com.tasks.gym_dashboard.data.local.room.GymDao
import com.tasks.gym_dashboard.data.local.room.GymDatabase
import com.tasks.gym_dashboard.data.remote.GymApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Singleton
    @Provides
    fun provideRetrofitBuilder(client: OkHttpClient) =
        Retrofit.Builder().baseUrl("https://fluttermsgs-default-rtdb.firebaseio.com/")
            .client(client).addConverterFactory(
                GsonConverterFactory.create()
            ).build().create<GymApi>()







    @Singleton
    @Provides
    fun provideHttpClient() =
        OkHttpClient.Builder()
            .build()

    @Singleton
    @Provides
    fun provideRoomDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, GymDatabase::class.java, "gyms_db")
        .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideGymDBDao(gymDatabase: GymDatabase) =
       gymDatabase.getDao


    @Singleton
    @Provides
    fun provideRepo(api: GymApi,gymDao: GymDao) =
        Repository(api,gymDao)




}