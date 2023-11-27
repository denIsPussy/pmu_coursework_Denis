package com.example.watchlinkapp.ComposeUI

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.watchlinkapp.ComposeUI.Movie.MovieCatalogViewModel
import com.example.watchlinkapp.ComposeUI.Movie.MovieViewModel
import com.example.watchlinkapp.ComposeUI.Movie.SearchViewModel
import com.example.watchlinkapp.ComposeUI.User.UserViewModel
import com.example.watchlinkapp.MovieApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            MovieCatalogViewModel(movieApplication().container.genreRepository)
        }
        initializer {
            SearchViewModel(movieApplication().container.movieRepository)
        }
        initializer {
            UserViewModel(movieApplication().container.userRepository)
        }
        initializer {
            MovieViewModel(
                this.createSavedStateHandle(),
                movieApplication().container.movieRepository
            )
        }
    }
}

fun CreationExtras.movieApplication(): MovieApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication)