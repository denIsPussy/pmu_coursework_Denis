package com.example.watchlinkapp.ComposeUI.Movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import com.example.watchlinkapp.Database.AppDataContainer
import com.example.watchlinkapp.Entities.Model.Genre.Genre
import com.example.watchlinkapp.Entities.Model.Genre.GenresWithMovies
import com.example.watchlinkapp.Entities.Model.Movie.Movie
import com.example.watchlinkapp.Entities.Model.MovieGenre.MovieGenreCrossRef
import com.example.watchlinkapp.Entities.Repository.Genre.GenreRepository
import com.example.watchlinkapp.Entities.Repository.Movie.MovieRepository
import com.example.watchlinkapp.Entities.Repository.MovieGenre.MovieGenreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieCatalogViewModel(
    private val genreRepository: GenreRepository,
    private val movieRepository: MovieRepository,
    private val movieGenreRepository: MovieGenreRepository,
) : ViewModel() {
    val genreListUiState: Flow<PagingData<Genre>> = genreRepository.getAll()
    val movieListUiState: Flow<PagingData<Movie>> = movieRepository.getAll()

    private val _movieGenreList = MutableLiveData<List<MovieGenreCrossRef>>()
    val movieGenreListUiState: LiveData<List<MovieGenreCrossRef>> = _movieGenreList

    init {
        loadMovieGenre()
    }

    private fun loadMovieGenre() {
        viewModelScope.launch(Dispatchers.IO) {
            val genres = movieGenreRepository.getAll()
            withContext(Dispatchers.Main) {
                _movieGenreList.postValue(genres)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getFilteredListMovies(searchString: String): Flow<PagingData<Movie>> {
        return movieListUiState
            .flatMapConcat { pagingData ->
                val filteredData = pagingData.filter { movie ->
                    movie.title.startsWith(searchString, ignoreCase = true)
                }
                flowOf(filteredData)
            }
    }

    fun containsMovieInGenre(movieId: Int, genreId: Int): Boolean{
        return movieGenreListUiState.value?.contains(MovieGenreCrossRef(movieId, genreId)) ?: false
    }
}