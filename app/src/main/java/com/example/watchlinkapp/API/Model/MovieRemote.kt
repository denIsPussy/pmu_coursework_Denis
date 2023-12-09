package com.example.watchlinkapp.API.Model

import androidx.room.ColumnInfo
import com.example.watchlinkapp.Entities.Model.Movie.Movie
import com.example.watchlinkapp.Entities.Model.User.User
import kotlinx.serialization.Serializable
import java.util.Base64

@Serializable
data class MovieRemote(
    val movieId: Int? = 0,
    val title: String = "",
    val releaseYear: Int = 0,
    val duration: Double = 0.0,
    val rating: Double = 0.0,
    val synopsis: String = "",
    val director: String = "",
    val image: String? = ""
)

fun MovieRemote.toMovie(): Movie = Movie(
    movieId,
    title,
    releaseYear,
    duration,
    rating,
    synopsis,
    director,
    Base64.getDecoder().decode(image)
)

fun Movie.toMovieRemote(): MovieRemote = MovieRemote(
    movieId,
    title,
    releaseYear,
    duration,
    rating,
    synopsis,
    director,
    image.toString()
)