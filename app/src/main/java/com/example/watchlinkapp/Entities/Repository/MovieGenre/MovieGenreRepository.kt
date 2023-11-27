package com.example.watchlinkapp.Entities.Repository.MovieGenre

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.watchlinkapp.Entities.Model.MovieGenre.MovieGenreCrossRef

interface MovieGenreRepository {
    suspend fun insert(movie: MovieGenreCrossRef)
    suspend fun update(movie: MovieGenreCrossRef)
    suspend fun delete(movie: MovieGenreCrossRef)
}