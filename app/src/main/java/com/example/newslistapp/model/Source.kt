package com.example.newslistapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetNewRequest(
    val country : String,
    val apiKey : String
): Parcelable