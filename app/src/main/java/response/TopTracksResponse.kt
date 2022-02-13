package response

import com.google.gson.annotations.SerializedName

data class TopTracksResponse(
    @SerializedName("toptracks")
    val toptracks: Toptracks = Toptracks()
)