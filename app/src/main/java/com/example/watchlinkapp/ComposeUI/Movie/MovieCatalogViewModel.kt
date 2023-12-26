package com.example.watchlinkapp.ComposeUI.Movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.watchlinkapp.API.Model.MovieGenreCountRemote
import com.example.watchlinkapp.API.Model.ReleaseYearRemote
import com.example.watchlinkapp.Entities.Model.Genre.Genre
import com.example.watchlinkapp.Entities.Model.Movie.Movie
import com.example.watchlinkapp.Entities.Model.MovieGenre.MovieGenreCrossRef
import com.example.watchlinkapp.Entities.Repository.Genre.GenreRepository
import com.example.watchlinkapp.Entities.Repository.Movie.MovieRepository
import com.example.watchlinkapp.Entities.Repository.MovieGenre.MovieGenreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieCatalogViewModel(
    private val genreRepository: GenreRepository,
    private val movieRepository: MovieRepository,
    private val movieGenreRepository: MovieGenreRepository,
) : ViewModel() {

    val movieListUiState: Flow<PagingData<Movie>> = movieRepository.getAll()
    val genreListUiState: Flow<PagingData<Genre>> = genreRepository.getAll()

    private val _movieGenreList = MutableLiveData<List<MovieGenreCrossRef>>()
    val movieGenreListUiState: LiveData<List<MovieGenreCrossRef>> = _movieGenreList

    private val _movieByDateListUiState = MutableStateFlow<List<Movie>>(emptyList())
    val movieByDateListUiState: Flow<List<Movie>> = _movieByDateListUiState.asStateFlow()

    private val _releaseYearListUiState = MutableStateFlow<ReleaseYearRemote>(ReleaseYearRemote())
    val releaseYearListUiState: Flow<ReleaseYearRemote> = _releaseYearListUiState.asStateFlow()

    private val _genreMovieCountListUiState = MutableStateFlow<List<MovieGenreCountRemote>>(listOf())
    val genreMovieCountListUiState: Flow<List<MovieGenreCountRemote>> = _genreMovieCountListUiState.asStateFlow()

    fun collectMoviesByDate(startDate: String, endDate: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _movieByDateListUiState.value = movieRepository.getMoviesByDate(startDate, endDate).first()
        }
    }

    init {
        loadMovieGenre()
        loadReleaseYears()
        loadGenreMovieCount()
    }

    private fun loadMovieGenre() {
        viewModelScope.launch(Dispatchers.IO) {
            val genres = movieGenreRepository.getAll()
            withContext(Dispatchers.Main) {
                _movieGenreList.postValue(genres)
            }
        }
    }

    private fun loadReleaseYears() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getReleaseYears().collect { releaseYears ->
                _releaseYearListUiState.value = releaseYears
            }
        }
    }

    private fun loadGenreMovieCount() {
        viewModelScope.launch(Dispatchers.IO) {
            movieGenreRepository.getCountMoviesByGenre().collect { genreMovieCount ->
                _genreMovieCountListUiState.value = genreMovieCount
            }
        }
    }

    fun containsMovieInGenre(movieId: Int, genreId: Int): Boolean{
        return movieGenreListUiState.value?.contains(MovieGenreCrossRef(movieId, genreId)) ?: false
    }
}