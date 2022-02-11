package com.example.musicapp

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val service = Retrofit.Builder()
            .baseUrl("http://ws.audioscrobbler.com/2.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)


        service.getArtistInfo().enqueue(object : Callback<ArtistBioResponse> {

            override fun onFailure(call: Call<ArtistBioResponse>, t: Throwable) {
                Log.d("TAG_", "An error happened!")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ArtistBioResponse>, response: Response<ArtistBioResponse>) {
                Log.d("TAG_", response.body().toString())
            }
        })
    }
}

class ArtistBioResponse(val results: ArtistDataResponse)

class ArtistDataResponse(val artist: List<ArtistInfoResponse>)

class ArtistInfoResponse(val data: ArtistInfoDataResponse)

class ArtistInfoDataResponse(val name: String, image: Image, val listeners: String, val playcount: String)



interface UserService {
    @GET("/artist.json")
    fun getArtistInfo(): Call<ArtistBioResponse>
}





