package com.example.musicapp.responseBio

import com.google.gson.annotations.SerializedName

data class Bio(
    @SerializedName("content")
    val content: String = "",
    @SerializedName("published")
    val published: String = "",
    @SerializedName("summary")
    val summary: String = ""
)