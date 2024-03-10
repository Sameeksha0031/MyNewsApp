package com.example.newslistapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newslistapp.repository.NewsRepository
import com.example.newslistapp.roomdb.SavedNewsDataBase
import com.example.newslistapp.viewModels.NewsViewModel
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

class NewsViewModelFactory  @Inject constructor(private val newsRepository:NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return modelClass.getConstructor(NewsViewModel::class.java)
//            .newInstance(instance)
        return NewsViewModel(newsRepository) as T
    }
}