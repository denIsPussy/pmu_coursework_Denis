package com.example.watchlinkapp.Entities.Repository.Genre

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.watchlinkapp.Entities.Model.Genre.Genre
import com.example.watchlinkapp.Entities.Model.Genre.GenresWithMovies
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun getAll(): List<Genre>
    fun getGenresWithMovies(): Flow<List<GenresWithMovies>>
    suspend fun insert(genre: Genre)
    suspend fun update(genre: Genre)
    suspend fun delete(genre: Genre)
}