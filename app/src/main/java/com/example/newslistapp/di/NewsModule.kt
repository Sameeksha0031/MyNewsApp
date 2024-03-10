package com.example.newslistapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newslistapp.retrofit.NewsServices
import com.example.newslistapp.roomdb.SavedNewsDataBase
import com.example.newslistapp.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object  NewsModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesNewsApi(retrofit: Retrofit) : NewsServices {
        return retrofit.create(NewsServices::class.java)
    }

    @Singleton
    @Provides
    fun providesRoomDB(context: Context) : SavedNewsDataBase {
        return Room.databaseBuilder(context.applicationContext,
            SavedNewsDataBase::class.java,
            "saved_news_database"
        ).fallbackToDestructiveMigration()
            .build()
    }
}