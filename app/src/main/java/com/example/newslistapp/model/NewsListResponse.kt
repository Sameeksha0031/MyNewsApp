package com.example.newslistapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsListResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
): Parcelable