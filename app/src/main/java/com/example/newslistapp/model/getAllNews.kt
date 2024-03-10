package com.example.newslistapp.model

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.net.URL

@Parcelize
data class getAllNewsResponse(
    var status: String,
    var totalResult: Int,
    var articles: ArrayList<newsDeatils>
) : Parcelable

@Parcelize
data class newsDeatils(
    var sources: sources,
    var author: String,
    var title: String,
    var description: String? = null,
    var url: URL,
    var urlToImage: String?= null,
    var publishedAt: String ,
    var content : String?= null
) : Parcelable

@Parcelize
data class sources(
    var id: String,
    var name: String
) : Parcelable

@Parcelize
data class getNewsOfCountry(
    var country: String,
    var apiKey: String
) : Parcelable