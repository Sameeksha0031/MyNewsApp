package com.example.newslistapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newslistapp.model.Article
import javax.inject.Inject

@Database(entities = [Article::class], version = 3, exportSchema = false)
@TypeConverters(Convertors::class)
abstract class SavedNewsDataBase : RoomDatabase() {

    abstract fun savedArticlesDao() : SavedArticlesDao

    /* companion object {
        @Volatile
        private var INSTANCE : SavedNewsDataBase ?= null

        fun getDataBase() : SavedNewsDataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SavedNewsDataBase::class.java,
                    "saved_news_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    } */
}