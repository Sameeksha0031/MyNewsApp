package com.example.newslistapp.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newslistapp.model.Article
import com.example.newslistapp.model.NewsListResponse
import com.example.newslistapp.repository.NewsRepository
import com.example.newslistapp.roomdb.SavedNewsDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(val newsRepository: NewsRepository): ViewModel() {

    private val _getNewsFlow = MutableSharedFlow<NewsListResponse>()
    val getNewsFlow : SharedFlow<NewsListResponse> = _getNewsFlow.shareIn(viewModelScope,
        SharingStarted.WhileSubscribed())

    fun getFilteredNewsList(country : String, apikey : String) {
        viewModelScope.launch {
            newsRepository.getNewsListByFilter(country,apikey).body()?.let { _getNewsFlow.emit(it) }
        }
    }

    private val _savedNewsFlow = MutableSharedFlow<List<Article>>()
    val savedNewsFlow : SharedFlow<List<Article>> = _savedNewsFlow.shareIn(viewModelScope,
        SharingStarted.WhileSubscribed())

    fun getArticlesFromDataBase(){
        viewModelScope.launch(Dispatchers.IO) {
            _savedNewsFlow.emit(newsRepository.showAllSavedArticles())
        }
    }

    fun addArticleToDataBase(article: Article){
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.addArticlesInSavedList(article)
        }
    }

    fun removeArticleFromDataBase(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.removedSavedArticlesFromList(id)
        }
    }

    init{
        viewModelScope.launch {
            if(newsRepository.getNewsList().isSuccessful) {
                newsRepository.getNewsList().body()?.let { _getNewsFlow.emit(it)
                    Log.d("#sam","it = $it")
                }
            }
        }
    }
}