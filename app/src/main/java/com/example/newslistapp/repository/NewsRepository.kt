package com.example.newslistapp.repository

import androidx.lifecycle.LiveData
import com.example.newslistapp.model.Article
import com.example.newslistapp.model.NewsListResponse
import com.example.newslistapp.retrofit.NewsServices
import com.example.newslistapp.roomdb.SavedNewsDataBase
import com.example.newslistapp.utils.Constants
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(val newsServices: NewsServices, val savedNewsDataBase: SavedNewsDataBase) {

    suspend fun getNewsList() : Response<NewsListResponse> {
        return newsServices.getNew("us",Constants.API_KEY)
    }

    suspend fun getNewsListByFilter(country : String, api_key : String) : Response<NewsListResponse> {
        return newsServices.getNew(country,api_key)
    }

    suspend fun showAllSavedArticles() : List<Article> = savedNewsDataBase.savedArticlesDao().showSavedArticles()

    suspend fun checkForDuplicateNews(publishDate : String) : Article? {
        return savedNewsDataBase.savedArticlesDao().checkForDuplicates(publishDate)
    }

    suspend fun addArticlesInSavedList(article: Article) {
        savedNewsDataBase.savedArticlesDao().checkForDuplicates(article)
    }

    suspend fun removedSavedArticlesFromList(id : Int) {
        savedNewsDataBase.savedArticlesDao().removeSavedArticle(id)
    }
}