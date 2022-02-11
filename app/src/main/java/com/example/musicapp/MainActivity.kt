package com.example.musicapp

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
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)


        service.getTopTracks().enqueue(object : Callback<ApiResponse> {

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.d("TAG_", "An error happened!")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                Log.d("TAG_", response.body().toString())
            }
        })
    }
}

data class ApiResponse(val results: List<Artist>)
data class Artist(val name: String, val track: String)

interface UserService {
    @GET("/artist")
    fun getTopTracks(): Call<ApiResponse>
}





