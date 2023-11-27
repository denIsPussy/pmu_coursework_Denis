package com.example.watchlinkapp.Entities.Repository.Genre

import com.example.watchlinkapp.Entities.DAO.Genre.GenreDAO
import com.example.watchlinkapp.Entities.Model.Genre.Genre
import com.example.watchlinkapp.Entities.Model.Genre.GenresWithMovies
import kotlinx.coroutines.flow.Flow

class OfflineGenreRepository(private val genreDAO: GenreDAO) : GenreRepository {
    override fun getAll(): List<Genre> = genreDAO.getAll()
    override fun getGenresWithMovies(): Flow<List<GenresWithMovies>> = genreDAO.getGenresWithMovies()
    override suspend fun insert(genre: Genre) = genreDAO.insert(genre)
    override suspend fun update(genre: Genre) = genreDAO.update(genre)
    override suspend fun delete(genre: Genre) = genreDAO.delete(genre)
}