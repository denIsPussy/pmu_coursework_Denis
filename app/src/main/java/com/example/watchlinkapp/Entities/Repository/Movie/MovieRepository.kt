package com.example.watchlinkapp.Entities.Repository.Movie

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.watchlinkapp.Entities.Model.Movie.Movie
import com.example.watchlinkapp.Entities.Model.Movie.MovieWithGenres
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getAll(): Flow<List<Movie>>
    fun getMoviesWithGenres(): Flow<List<MovieWithGenres>>
    fun getMovieWithGenres(id: Int): Flow<MovieWithGenres?>
    suspend fun insert(movie: Movie)
    suspend fun update(movie: Movie)
    suspend fun delete(movie: Movie)
}