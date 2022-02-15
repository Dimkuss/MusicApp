package com.example.musicapp

import responseTrack.Track

sealed interface SearchState {
    object Loading : SearchState
    data class Idle(val tracks: List<Track>? = null) : SearchState
    data class Error(val exception: Exception) : SearchState
}