package com.example.musicapp

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


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

            override fun onResponse(
                call: Call<ArtistBioResponse>,
                response: Response<ArtistBioResponse>
            ) {
                Log.d("TAG_", response.body().toString())
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}


class ArtistBioResponse(val results: ArtistDataResponse)

class ArtistDataResponse(val artist: List<ArtistInfoResponse>)

class ArtistInfoResponse(val data: ArtistInfoDataResponse)

class ArtistInfoDataResponse(
    val name: String,
    image: Image,
    val listeners: String,
    val playcount: String
)


interface UserService {
    @GET("/artist.json")
    fun getArtistInfo(): Call<ArtistBioResponse>
}





