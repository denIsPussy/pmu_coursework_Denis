package com.example.watchlinkapp.Entities.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.watchlinkapp.Entities.Model.Genre
import com.example.watchlinkapp.Entities.Model.GenresWithMovies
import com.example.watchlinkapp.Entities.Model.Movie
import com.example.watchlinkapp.Entities.Model.MovieWithGenres
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDAO {
    @Query("select * from genres")
    fun getAll(): List<Genre>

    @Query("select * from genres")
    fun getGenresWithMovies(): Flow<List<GenresWithMovies>>

    @Insert
    suspend fun insert(genre: Genre)

    @Update
    suspend fun update(genre: Genre)

    @Delete
    suspend fun delete(genre: Genre)
}