package com.example.watchlinkapp.Entities.Repository.Movie

import com.example.watchlinkapp.Entities.DAO.Genre.GenreDAO
import com.example.watchlinkapp.Entities.DAO.Movie.MovieDAO
import com.example.watchlinkapp.Entities.Model.Movie.Movie
import com.example.watchlinkapp.Entities.Model.Movie.MovieWithGenres
import kotlinx.coroutines.flow.Flow

class OfflineMovieRepository(private val movieDAO: MovieDAO) : MovieRepository {
    override fun getAll(): Flow<List<Movie>> = movieDAO.getAll()

    override fun getMoviesWithGenres(): Flow<List<MovieWithGenres>> = movieDAO.getMoviesWithGenres()

    override fun getMovieWithGenres(id: Int): Flow<MovieWithGenres?> = movieDAO.getMovieWithGenres(id)

    override suspend fun insert(movie: Movie) = movieDAO.insert(movie)

    override suspend fun update(movie: Movie) = movieDAO.update(movie)

    override suspend fun delete(movie: Movie) = movieDAO.delete(movie)
}