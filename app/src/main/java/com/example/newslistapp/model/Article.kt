package com.example.newslistapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serial
import java.io.Serializable


@Entity(tableName = "article_table")
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id : Int ,
    val author: String ?= null,
    val content: String ?= null,
    val description: String ?= null,
    val publishedAt: String ?= null,
    val source: Source,
    val title: String ?= null,
    val url: String ?= null,
    val urlToImage: String ?= null
): Serializable

data class Source(
    val id: String ?= null,
    val name: String ?= null
)