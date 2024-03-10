package com.example.newslistapp.retrofit

import com.example.newslistapp.model.NewsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    @GET("/v2/top-headlines")
    suspend fun getNew(
        @Query("country") country : String,
        @Query("apiKey") apiKey : String
    ) : Response<NewsListResponse>
}