package com.example.musicapp.responseTrack

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("artist")
    val artist: Artist = Artist(),
    @SerializedName("@attr")
    val attr: AttrX = AttrX(),
    @SerializedName("image")
    val image: List<Image> = listOf(),
    @SerializedName("listeners")
    val listeners: String = "",
    @SerializedName("mbid")
    val mbid: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("playcount")
    val playcount: String = "",
    @SerializedName("streamable")
    val streamable: String = "",
    @SerializedName("url")
    val url: String = ""
)