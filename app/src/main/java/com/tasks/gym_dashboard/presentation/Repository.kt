package com.tasks.gym_dashboard.presentation

import com.tasks.gym_dashboard.api.GymApi
import com.tasks.gym_dashboard.api.consumer.GymApiConsumer
import com.tasks.gym_dashboard.data.GymsApplication
import com.tasks.gym_dashboard.data.model.GymStateItem
import com.tasks.gym_dashboard.data.room.GymDao
import com.tasks.gym_dashboard.data.room.GymDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository {
    val gymDao: GymDao by lazy {
        GymDatabase.getDao(GymsApplication.getContext())
    }
    val gymApi: GymApi

    init {
        gymApi = GymApiConsumer().getApi(GymsApplication.getContext())
    }


      suspend fun updateSavedListDatabase() {
        val listOfGyms = gymApi.listOfGyms()

        val listOfFavoriteGyms=gymDao.getFavoriteGymList()

        val map = listOfFavoriteGyms.map {
            GymStateItem(it.id, is_Favorite = true)
        }

        gymDao.insertAll(listOfGyms)
        gymDao.updateAllGyms(map)
    }

    suspend   fun getList() = withContext(Dispatchers.IO) {

        try {
            updateSavedListDatabase()
        } catch (e: Exception) {
            if(gymDao.getAll().isEmpty())
            {
                throw  Exception("No Data Found Exception, Try reconnect!!")

            }
        }

        gymDao.getAll()


    }
    suspend   fun getGym(id: Int) = withContext(Dispatchers.IO) {
        gymApi.itemGym("\"id\"", id)
    }






}