package com.example.watchlinkapp.Entities.DAO.Genre

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.watchlinkapp.Entities.Model.Genre.Genre
import com.example.watchlinkapp.Entities.Model.Genre.GenresWithMovies
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDAO {
    @Query("select * from genres")
    fun getAll(): PagingSource<Int, Genre>
    @Query("select * from genres")
    fun getAllGenres(): List<Genre>
    @Query("select * from genres where genreId = :id")
    fun getGenre(id: Int): Genre
    @Insert
    suspend fun insert(vararg genre: Genre)
    @Update
    suspend fun update(genre: Genre)
    @Delete
    suspend fun delete(genre: Genre)
    @Query("DELETE FROM genres")
    suspend fun deleteAll()
}