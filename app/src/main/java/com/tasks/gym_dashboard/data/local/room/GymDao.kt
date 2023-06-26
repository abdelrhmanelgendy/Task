package com.tasks.gym_dashboard.data.local.room

import androidx.room.*
import com.tasks.gym_dashboard.data.local.GymStateItem
import com.tasks.gym_dashboard.data.local.LocalGym

@Dao
interface GymDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(gyms:List<LocalGym>)

    @Query("SELECT * FROM Gyms_table")
    suspend fun getAll():List<LocalGym>

    @Update(LocalGym::class)
    suspend fun update(gymStateItem: GymStateItem)

    @Query("SELECT * FROM Gyms_table WHERE  is_Favorite=1")
    suspend fun getFavoriteGymList():List<LocalGym>


    @Update(LocalGym::class)
    suspend fun updateAllGyms(gymStatesItem: List<GymStateItem>)

}