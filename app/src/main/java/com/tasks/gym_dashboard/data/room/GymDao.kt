package com.tasks.gym_dashboard.data.room

import androidx.room.*
import com.tasks.gym_dashboard.data.model.GymItem
import com.tasks.gym_dashboard.data.model.GymStateItem

@Dao
interface GymDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(gyms:List<GymItem>)

    @Query("SELECT * FROM Gyms_table")
    suspend fun getAll():List<GymItem>

    @Update(GymItem::class)
    suspend fun update(gymStateItem: GymStateItem)

    @Query("SELECT * FROM Gyms_table WHERE  is_Favorite=1")
    suspend fun getFavoriteGymList():List<GymItem>


    @Update(GymItem::class)
    suspend fun updateAllGyms(gymStatesItem: List<GymStateItem>)

}