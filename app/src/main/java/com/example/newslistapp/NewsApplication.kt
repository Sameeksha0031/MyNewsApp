package com.example.newslistapp

import android.app.Application
import android.app.KeyguardManager
import android.content.Context
import android.content.res.Configuration
import com.example.newslistapp.di.ApplicationComponent
import com.example.newslistapp.di.DaggerApplicationComponent
import com.example.newslistapp.di.NewsModule
import com.google.firebase.inject.Provider

open class NewsApplication :  Application() {

   lateinit var applicationComponent: ApplicationComponent

//    val appComponent: ApplicationComponent by lazy {
//        DaggerApplicationComponent.builder()
//            .newsModule(NewsModule)
//            .build()
//    }

    override fun onCreate() {
        super.onCreate()
//        applicationComponent = DaggerApplicationComponent.builder()
//            .newsModule(NewsModule)
//            .build()
//        applicationComponent.inject(this)
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}