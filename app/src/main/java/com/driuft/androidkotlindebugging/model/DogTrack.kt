package com.driuft.androidkotlindebugging.model

import java.io.Serializable

data class DogTrack(
    val id: String,
    val title: String,
    val artist: String,
    val duration: String,
    val imageUrl: String,
    val audioUrl: String
) : Serializable
