package com.example.watchlinkapp.Entities.DAO.Movie

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.watchlinkapp.Entities.Model.Movie.Movie
import com.example.watchlinkapp.Entities.Model.Movie.MovieWithGenres
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {
    @Query("select * from movies")
    fun getAll(): Flow<List<Movie>>

    @Query("select * from movies")
    fun getMoviesWithGenres(): Flow<List<MovieWithGenres>>

    @Query("select * from movies where movieId = :id")
    fun getMovieWithGenres(id: Int): Flow<MovieWithGenres?>

    @Insert
    suspend fun insert(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)
}