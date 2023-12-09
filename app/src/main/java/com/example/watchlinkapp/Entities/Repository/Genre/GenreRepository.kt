package com.example.watchlinkapp.Entities.Repository.Genre

import androidx.paging.PagingData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.watchlinkapp.Entities.Model.Genre.Genre
import com.example.watchlinkapp.Entities.Model.Genre.GenresWithMovies
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun getAll(): Flow<PagingData<Genre>>
    suspend fun getGenre(id: Int): Genre
    suspend fun getAllGenres(): Flow<List<Genre>>
    suspend fun insert(genre: Genre)
    suspend fun update(genre: Genre)
    suspend fun delete(genre: Genre)
}