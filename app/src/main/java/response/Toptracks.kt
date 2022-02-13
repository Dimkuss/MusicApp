package response

import com.google.gson.annotations.SerializedName


data class Toptracks(
    @SerializedName("@attr")
    val attr: Attr = Attr(),
    @SerializedName("track")
    val track: List<Track> = listOf()
)