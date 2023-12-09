package com.example.watchlinkapp.Entities.Repository.MovieGenre

import com.example.watchlinkapp.Entities.DAO.Movie.MovieDAO
import com.example.watchlinkapp.Entities.DAO.MovieGenre.MovieWithGenresDAO
import com.example.watchlinkapp.Entities.Model.MovieGenre.MovieGenreCrossRef

class OfflineMovieGenreRepository(private val movieWithGenreDAO: MovieWithGenresDAO) : MovieGenreRepository {
    override suspend fun getAll() = movieWithGenreDAO.getAll()
    override suspend fun insert(movie: MovieGenreCrossRef) = movieWithGenreDAO.insert(movie)
    override suspend fun delete(movie: MovieGenreCrossRef) = movieWithGenreDAO.delete(movie)
    suspend fun deleteAll() = movieWithGenreDAO.deleteAll()
    suspend fun insertAll(movieGenre: List<MovieGenreCrossRef>) =
        movieWithGenreDAO.insert(*movieGenre.toTypedArray())
}