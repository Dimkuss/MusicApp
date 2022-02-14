package com.example.musicapp

import responseBio.Artist

sealed interface BioState {
    object Loading : BioState
    data class Success(val artist: Artist) : BioState
    data class Error(val exception: Exception) : BioState

}