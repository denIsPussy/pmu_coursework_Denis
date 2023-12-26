package com.example.watchlinkapp.API.Model

import com.example.watchlinkapp.Entities.Model.Genre.Genre
import kotlinx.serialization.Serializable

@Serializable
data class MovieGenreCountRemote(
    val genre: GenreRemote? = null,
    val movieCount: Int = 0
)