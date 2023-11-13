package com.example.watchlinkapp.Entities.Model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class GenresWithMovies(
    @Embedded val genre: Genre,
    @Relation(
        parentColumn = "genreId",
        entityColumn = "movieId",
        associateBy = Junction(MovieGenreCrossRef::class)
    )
    val movies: List<Movie>
)
