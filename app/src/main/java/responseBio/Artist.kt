package responseBio

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("bio")
    val bio: String = "",
    @SerializedName("image")
    val image: List<Image> = listOf(),
    @SerializedName("mbid")
    val mbid: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("ontour")
    val ontour: String = "",
    @SerializedName("similar")
    val similar: String = "",
    @SerializedName("stats")
    val stats: String = "",
    @SerializedName("streamable")
    val streamable: String = "",
    @SerializedName("tags")
    val tags: String = "",
    @SerializedName("url")
    val url: String = ""
)