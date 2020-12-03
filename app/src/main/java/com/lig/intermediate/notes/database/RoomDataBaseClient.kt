package com.lig.intermediate.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lig.intermediate.notes.models.*

const val DATABASE_VERSION = 1
const val DB_NAME = "local_db"

@Database(entities = [TaskEntity::class, Todo::class, Tag::class, Note::class], version = DATABASE_VERSION)
abstract class RoomDataBaseClient: RoomDatabase() {
    companion object{

        private var instance: RoomDataBaseClient? = null  // create a singleton

        fun getInstance(context: Context): RoomDataBaseClient{
            if(instance==null){
                instance = createDataBase(context)
            }
            return instance!!
        }

        private fun createDataBase(context: Context): RoomDataBaseClient {
            return Room.databaseBuilder(context, RoomDataBaseClient::class.java, DB_NAME).build()
        }
    }



}