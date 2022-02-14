package com.example.musicapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import api.MusicApi
import java.io.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _data = MutableStateFlow<SearchState>(SearchState.Loading)
    val data: StateFlow<SearchState> = _data.asStateFlow()

    fun searchTracks(query: String) {
        viewModelScope.launch {
            val result = try {
                SearchState.Success(
                    MusicApi.service.getArtistTracks(query).toptracks.track
                )
            } catch (e: IOException) {
                SearchState.Error(e)
            }

            _data.value = result
        }
    }

}