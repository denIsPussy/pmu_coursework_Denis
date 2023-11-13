package com.example.watchlinkapp.Entities.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.watchlinkapp.Entities.Model.Movie
import com.example.watchlinkapp.Entities.Model.MovieGenreCrossRef
import com.example.watchlinkapp.Entities.Model.MovieWithGenres
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {
    @Query("select * from movies")
    fun getAll(): List<Movie>

    @Query("select * from movies")
    fun getMoviesWithGenres(): Flow<List<MovieWithGenres>>

    @Query("select * from movies where movieId = :id")
    fun getMovieWithGenres(id: Int): MovieWithGenres

    @Insert
    suspend fun insert(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)
}