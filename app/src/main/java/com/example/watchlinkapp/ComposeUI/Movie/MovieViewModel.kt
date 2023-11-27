package com.example.watchlinkapp.ComposeUI.Movie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlinkapp.Entities.Model.Genre.Genre
import com.example.watchlinkapp.Entities.Model.Movie.Movie
import com.example.watchlinkapp.Entities.Model.Movie.MovieWithGenres
import com.example.watchlinkapp.Entities.Repository.Movie.MovieRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MovieViewModel(
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository
) : ViewModel() {

    var movieUiState by mutableStateOf(MovieUiState())
        private set

    private val movieUid: Int = checkNotNull(savedStateHandle["id"])

    init {
        viewModelScope.launch {
            if (movieUid > 0) {
                movieUiState = movieRepository.getMovieWithGenres(movieUid)
                    .filterNotNull()
                    .first()
                    .toUiState()
            }
        }
    }
}

data class MovieUiState(
    val movieDetails: MovieDetails = MovieDetails()
)

data class MovieDetails(
    val movie: Movie? = null,
    val genres: List<Genre> = listOf()
)

fun MovieWithGenres.toDetails(): MovieDetails = MovieDetails(
    movie = movie,
    genres = genres
)

fun MovieWithGenres.toUiState(): MovieUiState = MovieUiState(
    movieDetails = this.toDetails()
)

fun List<Genre>.toCustomString(): String{
    var genresString: String = ""
    this.forEach { genre ->
        genresString += "${genre.name}, "
    }
    return genresString.dropLast(2)
}
