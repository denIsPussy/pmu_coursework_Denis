package com.example.watchlinkapp.Entities.DAO.MovieGenre

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.watchlinkapp.Entities.Model.MovieGenre.MovieGenreCrossRef

@Dao
interface MovieWithGenresDAO {
    @Insert
    suspend fun insert(movie: MovieGenreCrossRef)

    @Update
    suspend fun update(movie: MovieGenreCrossRef)

    @Delete
    suspend fun delete(movie: MovieGenreCrossRef)
}