package com.example.youranimelist.viewmodel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.youranimelist.objects.Anime

@Database(entities = [Anime::class], version = 1)
abstract class AppDatabase: RoomDatabase() {


    companion object{

        private var appDatabase: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null)
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "main DB").build()
            return appDatabase!!
        }
    }

    abstract fun animesDao(): AnimesDao

}
