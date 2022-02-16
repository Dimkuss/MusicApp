package com.example.musicapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import api.MusicApi
import com.example.musicapp.BioState
import java.io.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BioViewModel : ViewModel() {

    private val _data = MutableStateFlow<BioState>(BioState.Idle())
    val data: StateFlow<BioState> = _data.asStateFlow()

    fun searchBio(query: String) {
        viewModelScope.launch {
            val result = try {
                BioState.Idle(
                    MusicApi.service.getArtistBio(query).artist
                )
            } catch (e: IOException) {
                BioState.Error(e)
            }

            _data.value = result
        }
    }

}