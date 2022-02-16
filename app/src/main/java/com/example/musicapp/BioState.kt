package com.example.musicapp

import com.example.musicapp.responseBio.Artist

sealed interface BioState {
    object Loading : BioState
    data class Error(val exception: Exception) : BioState
    data class Idle(val artist: Artist? = null) : BioState

}