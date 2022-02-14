package com.example.musicapp

import responseTrack.Track

sealed interface SearchState {
    object Loading : SearchState
    data class Success(val tracks: List<Track>) : SearchState
    data class Error(val exception: Exception) : SearchState

}