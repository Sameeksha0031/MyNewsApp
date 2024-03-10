package com.example.newslistapp.di

import android.content.Context
import com.example.newslistapp.MainActivity
import com.example.newslistapp.NewsApplication
import com.example.newslistapp.NewsListFragment
import com.example.newslistapp.SavedArticlesFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsModule::class])
interface ApplicationComponent  {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }

    fun inject(newsListFragment: NewsListFragment)
    fun inject(savedArticlesFragment : SavedArticlesFragment)
}