package responseBio

import com.google.gson.annotations.SerializedName

data class ImageX(
    @SerializedName("#text")
    val text : String,
    @SerializedName("size")
    val size: String
)