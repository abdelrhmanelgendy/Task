package com.tasks.gym_dashboard.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tasks.gym_dashboard.data.local.LocalGym

@Database(entities = arrayOf(LocalGym::class), version = 1)
abstract class GymDatabase() : RoomDatabase() {
    abstract val getDao: GymDao

    companion object {
        private var dao: GymDao? = null

        fun getDao(context: Context): GymDao {
            synchronized(this)
            {
                if (dao == null) {
                    dao = buildDB(context).getDao
                }
                return dao!!
            }
        }

        fun buildDB(context: Context) =
            Room.databaseBuilder(context.applicationContext, GymDatabase::class.java, "gyms_db")
                .fallbackToDestructiveMigration().build()
    }


}