package responseBio

import com.google.gson.annotations.SerializedName

data class ArtistX(
    @SerializedName("image")
    val image: List<ImageX> = listOf(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = ""
)