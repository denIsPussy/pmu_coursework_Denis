package com.example.watchlinkapp.Database

import android.content.Context
import com.example.watchlinkapp.Entities.Repository.Genre.GenreRepository
import com.example.watchlinkapp.Entities.Repository.Genre.OfflineGenreRepository
import com.example.watchlinkapp.Entities.Repository.Movie.MovieRepository
import com.example.watchlinkapp.Entities.Repository.Movie.OfflineMovieRepository
import com.example.watchlinkapp.Entities.Repository.MovieGenre.MovieGenreRepository
import com.example.watchlinkapp.Entities.Repository.MovieGenre.OfflineMovieGenreRepository
import com.example.watchlinkapp.Entities.Repository.User.OfflineUserRepository
import com.example.watchlinkapp.Entities.Repository.User.UserRepository

interface AppContainer {
    val movieRepository: MovieRepository
    val genreRepository: GenreRepository
    val userRepository: UserRepository
    val movieGenreRepository: MovieGenreRepository
}
class AppDataContainer(private val context: Context) : AppContainer {
    override val movieRepository: MovieRepository by lazy {
        OfflineMovieRepository(AppDatabase.getInstance(context).movieDao())
    }
    override val genreRepository: GenreRepository by lazy {
        OfflineGenreRepository(AppDatabase.getInstance(context).genreDao())
    }
    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(AppDatabase.getInstance(context).userDao())
    }
    override val movieGenreRepository: MovieGenreRepository by lazy {
        OfflineMovieGenreRepository(AppDatabase.getInstance(context).movieWithGenresDao())
    }

    companion object {
        const val TIMEOUT = 5000L
    }
}