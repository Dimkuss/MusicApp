package api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import responseBio.AuthorBioResponse
import responseTrack.TopTracksResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

private const val apiKey = "c11016bf99eacfa8159ac5c66993770e"
private const val apiKeyHeader = "api_key=$apiKey"
private const val formatHeader = "format=json"
private const val headers = "&$apiKeyHeader&$formatHeader"

interface MusicService {
    @GET("?method=artist.gettoptracks$headers")
    suspend fun getArtistTracks(
        @Query("artist") artist: String,
        @Query("limit") limit: Int = 5,
    ): TopTracksResponse

    @GET("?method=artist.getinfo$headers")
    suspend fun getArtistBio(
        @Query("artist") artist: String,
    ): AuthorBioResponse

}

object MusicApi {
    val service: MusicService by lazy {
        Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .baseUrl("http://ws.audioscrobbler.com/2.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}