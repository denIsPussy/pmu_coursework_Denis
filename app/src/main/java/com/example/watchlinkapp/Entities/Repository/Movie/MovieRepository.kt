package com.example.watchlinkapp.Entities.Repository.Movie

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.watchlinkapp.Entities.Model.Movie.Movie
import com.example.watchlinkapp.Entities.Model.Movie.MovieWithGenres
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getAll(): Flow<PagingData<Movie>>
    suspend fun getMovie(id: Int): Movie
    suspend fun insert(movie: Movie)
    suspend fun update(movie: Movie)
    suspend fun delete(movie: Movie)
}