package com.example.watchlinkapp.ComposeUI.Movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlinkapp.Database.AppDataContainer
import com.example.watchlinkapp.Entities.Model.Genre.GenresWithMovies
import com.example.watchlinkapp.Entities.Model.Movie.Movie
import com.example.watchlinkapp.Entities.Model.Movie.MovieWithGenres
import com.example.watchlinkapp.Entities.Repository.Genre.GenreRepository
import com.example.watchlinkapp.Entities.Repository.Movie.MovieRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SearchViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {
    val movieListUiState: StateFlow<MovieSearchListUiState> = movieRepository.getAll().map {
        MovieSearchListUiState(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = AppDataContainer.TIMEOUT),
        initialValue = MovieSearchListUiState()
    )
    fun getFilteredListMovies(searchString: String): List<Movie>{
        var movies = movieListUiState.value.movieList
        return movies.filter { movie -> movie.title.startsWith(searchString, ignoreCase = true) }
    }
}

data class MovieSearchListUiState(val movieList: List<Movie> = listOf())