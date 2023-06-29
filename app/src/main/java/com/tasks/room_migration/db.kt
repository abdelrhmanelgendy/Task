//package com.tasks.room_migration
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.room.migration.Migration
//import androidx.sqlite.db.SupportSQLiteDatabase
//
//
//abstract class db :RoomDatabase(
//
//) {
//    fun getRoom(context: Context): db {
//
//        return Room.databaseBuilder(context = context,db::class.java,"")
//            .addMigrations(migration)
//            .build()
//    }
//    val migration =object :Migration(1,5){
//        override fun migrate(database: SupportSQLiteDatabase) {
//
//            database.execSQL("CREATE TAB")
//        }
//    }
//
//
//
//}