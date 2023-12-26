package com.example.watchlinkapp.ComposeUI.Movie

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.watchlinkapp.API.Model.MovieGenreCountRemote
import com.example.watchlinkapp.ComposeUI.AppViewModelProvider

@Composable
fun Report(
    navController: NavController,
    viewModel: MovieCatalogViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val genreMovieCountListUiState =
        viewModel.genreMovieCountListUiState.collectAsState(initial = listOf())
    val genreMovieCountList = genreMovieCountListUiState.value
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Text(color = Color.White, text = "Жанр", modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(16.dp))
                Text(color = Color.White, text = "Количество фильмов", modifier = Modifier.weight(1f))
            }
        }

        items(genreMovieCountList) { movieGenreCount ->
            MovieGenreCountRow(movieGenreCount)
        }
    }
}

@Composable
fun MovieGenreCountRow(movieGenreCount: MovieGenreCountRemote) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(color = Color.White, text = movieGenreCount.genre!!.name, modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(16.dp))
        Text(color = Color.White, text = movieGenreCount.movieCount.toString(), modifier = Modifier.weight(1f))
    }
}