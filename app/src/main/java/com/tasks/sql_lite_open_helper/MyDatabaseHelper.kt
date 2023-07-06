package com.tasks.sql_lite_open_helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(private val context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {


    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "my_db"
        private const val TABLE_NAME = "my_table"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_AGE = "age"
        private const val COLUMN_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_NAME TEXT, $COLUMN_AGE INTEGER, $COLUMN_EMAIL TEXT)"


        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val dropTable =
            "DROP TABLE IF EXISTS $TABLE_NAME"
        p0?.let {
            it.execSQL(dropTable)
            onCreate(it)

        }
    }

    fun insertData(person:PersonData): Long {
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, person.name)
        contentValues.put(COLUMN_AGE, person.age)
        contentValues.put(COLUMN_EMAIL, person.email)

        val db = getWritableDataBase()
        return db.insert(TABLE_NAME, null, contentValues)
    }

    fun updateData(person:PersonData): Int {
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, person.name)
        contentValues.put(COLUMN_AGE, person.age)
        contentValues.put(COLUMN_EMAIL, person.email)

        val db = getWritableDataBase()
        return db.update(TABLE_NAME, contentValues, "$COLUMN_ID=?", arrayOf(person.id.toString()))
    }


    fun getData(id: Int): PersonData {
        val db = getReadableDataBase()
        val cursor =
            db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID=?", arrayOf(id.toString()))

        if(cursor.moveToFirst())
        {
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val age = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE))
            val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            return  PersonData(id,name,age,email)
        }
        return  PersonData.EMPTY

    }

    fun deleteData(id: Int): Int {
        val db = getWritableDataBase()
        return db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(id.toString()))
    }

    fun getAllData(): List<PersonData> {
        val db = getReadableDataBase()
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)


        val persons = arrayListOf<PersonData>()
        if(cursor.moveToFirst())
        {
            do {
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val age = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE))
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
                persons.add(PersonData(id,name,age,email))

            }while (cursor.moveToNext())
        }

        return  persons
    }
    private fun getReadableDataBase()=readableDatabase
    private fun getWritableDataBase()=writableDatabase
}

data class PersonData(val id:Int=-1,val name:String,val age:Int,val email: String){
    companion object{
        val EMPTY =PersonData(-1,"",-1,"")
    }
}