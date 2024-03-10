package com.example.newslistapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.newslistapp.model.Article

@Dao
interface SavedArticlesDao {

    @Transaction
    suspend fun checkForDuplicates(article : Article) {
        val existArticle = article.publishedAt?.let { checkForDuplicates(it) }
        if(existArticle == null) {
            insertArticles(article)
        }
    }

    @Insert
    suspend fun insertArticles(article: Article)

    @Query("SELECT * FROM article_table WHERE publishedAt= :publishDate")
    suspend fun checkForDuplicates(publishDate : String) : Article?

    @Query("SELECT * FROM article_table")
    suspend fun showSavedArticles() : List<Article>

    @Query("DELETE FROM article_table WHERE id = :articleId")
    suspend fun removeSavedArticle(articleId : Int)
}