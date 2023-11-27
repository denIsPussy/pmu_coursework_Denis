package com.example.watchlinkapp.ComposeUI.Movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlinkapp.Database.AppDataContainer
import com.example.watchlinkapp.Entities.Model.Genre.GenresWithMovies
import com.example.watchlinkapp.Entities.Model.Movie.Movie
import com.example.watchlinkapp.Entities.Repository.Genre.GenreRepository
import com.example.watchlinkapp.Entities.Repository.Movie.MovieRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MovieCatalogViewModel(
    private val genreRepository: GenreRepository
) : ViewModel() {
    val movieListUiState: StateFlow<MovieListUiState> = genreRepository.getGenresWithMovies().map {
        MovieListUiState(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = AppDataContainer.TIMEOUT),
        initialValue = MovieListUiState()
    )
}

data class MovieListUiState(val movieList: List<GenresWithMovies> = listOf())