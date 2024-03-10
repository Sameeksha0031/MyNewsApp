package com.example.newslistapp

import com.example.newslistapp.model.NewsListResponse
import retrofit2.Response
import javax.inject.Singleton


@Singleton
interface NewsRepositoryImpl {
    suspend fun getNews(country : String, apiKey : String) : Response<NewsListResponse>
}