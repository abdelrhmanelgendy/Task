package com.tasks.gym_dashboard.data

import com.tasks.gym_dashboard.data.remote.GymApi
import com.tasks.gym_dashboard.api.consumer.GymApiConsumer
import com.tasks.gym_dashboard.data.local.GymStateItem
import com.tasks.gym_dashboard.data.local.LocalGym
import com.tasks.gym_dashboard.data.local.room.GymDao
import com.tasks.gym_dashboard.data.local.room.GymDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val gymApi: GymApi,private val gymDao: GymDao) {




    suspend fun updateSavedListDatabase() {

        val listOfGyms = gymApi.listOfGyms()

        val listOfFavoriteGyms = gymDao.getFavoriteGymList()

        val map = listOfFavoriteGyms.map {
            GymStateItem(it.id, is_Favorite = true)
        }

        gymDao.insertAll(listOfGyms.map {
            LocalGym(it.gym_name,it.gym_location,it.id,it.is_open,it.is_Favorite)
        })
        gymDao.updateAllGyms(map)
    }

    suspend fun getList() = withContext(Dispatchers.IO) {

        try {
            updateSavedListDatabase()
        } catch (e: Exception) {
            if (gymDao.getAll().isEmpty()) {
                throw  Exception("No Data Found Exception, Try reconnect!!")

            }
        }

        gymDao.getAll().sortedBy {
            it.gym_name
        }


    }



    suspend fun toggleSavedGym(gymId: Int, newState: Boolean)= withContext(Dispatchers.IO) {
        val favoriteStatus = newState.not()
        val gymStateItem = GymStateItem(gymId, favoriteStatus)
        gymDao.update(gymStateItem)
        return@withContext gymDao.getAll()



    }

}

 