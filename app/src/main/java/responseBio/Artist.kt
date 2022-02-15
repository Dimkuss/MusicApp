package responseBio

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("bio")
    val bio: Bio = Bio(),
    @SerializedName("image")
    val image: List<Image> = listOf(),
    @SerializedName("mbid")
    val mbid: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("ontour")
    val ontour: String = "",
    @SerializedName("stats")
    val stats: Stats = Stats(),
    @SerializedName("streamable")
    val streamable: String = "",
    @SerializedName("url")
    val url: String = ""
)