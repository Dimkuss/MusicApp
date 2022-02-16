package com.example.musicapp.responseBio

import com.google.gson.annotations.SerializedName

data class AuthorBioResponse(
    @SerializedName("artist")
    val artist: Artist = Artist()
)