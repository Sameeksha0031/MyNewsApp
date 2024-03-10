package com.example.newslistapp.roomdb

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.newslistapp.model.Article
import com.example.newslistapp.model.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Convertors {

    @TypeConverter
    fun fromArrayList(value : Source) : String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toArrayList(value : String) : Source {
        val listType = object  : TypeToken<Source>(){}.type
        return Gson().fromJson(value,listType)
    }

}