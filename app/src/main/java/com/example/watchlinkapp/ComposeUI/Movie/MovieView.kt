package com.example.watchlinkapp.ComposeUI.Movie

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.watchlinkapp.ComposeUI.AppViewModelProvider
import com.example.watchlinkapp.Database.AppDatabase
import com.example.watchlinkapp.Entities.Model.Genre.Genre
import com.example.watchlinkapp.Entities.Model.Movie.MovieWithGenres
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieView(viewModel: MovieViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
    ) {
        var movie = viewModel.movieUiState.movieDetails.movie
        var genres = viewModel.movieUiState.movieDetails.genres
        movie?.let {
            LazyColumn(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                item {
                    val decodedBitmap = BitmapFactory.decodeByteArray(movie.image, 0, movie.image!!.size)
                    val imageBitmap = decodedBitmap.asImageBitmap()
                    Image(
                        bitmap = imageBitmap,
                        contentDescription = null,
                        modifier = Modifier
                            //.fillMaxHeight()
                            .width(200.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                item {
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        OutlinedTextField(
                            readOnly = true,
                            value = movie.title,
                            onValueChange = { /*TODO*/ },
                            label = { Text("Title", color = Color.LightGray) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedTextColor = Color.LightGray,
                                focusedBorderColor = Color.Gray, // Цвет рамки при фокусе
                                unfocusedBorderColor = Color.Gray // Цвет рамки при отсутствии фокуса
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            readOnly = true,
                            value = genres.toCustomString(),
                            onValueChange = { /*TODO*/ },
                            label = { Text("Genre", color = Color.LightGray) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedTextColor = Color.LightGray,
                                focusedBorderColor = Color.Gray, // Цвет рамки при фокусе
                                unfocusedBorderColor = Color.Gray // Цвет рамки при отсутствии фокуса
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            readOnly = true,
                            value = "${movie.releaseYear}",
                            onValueChange = { /*TODO*/ },
                            label = { Text("Release Year", color = Color.LightGray) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedTextColor = Color.LightGray,
                                focusedBorderColor = Color.Gray, // Цвет рамки при фокусе
                                unfocusedBorderColor = Color.Gray // Цвет рамки при отсутствии фокуса
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            readOnly = true,
                            value = "${movie.duration} minutes",
                            onValueChange = { /*TODO*/ },
                            label = { Text("Duration", color = Color.LightGray) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedTextColor = Color.LightGray,
                                focusedBorderColor = Color.Gray, // Цвет рамки при фокусе
                                unfocusedBorderColor = Color.Gray // Цвет рамки при отсутствии фокуса
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            readOnly = true,
                            value = "${movie.rating}",
                            onValueChange = { /*TODO*/ },
                            label = { Text("Rating", color = Color.LightGray) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedTextColor = Color.LightGray,
                                focusedBorderColor = Color.Gray, // Цвет рамки при фокусе
                                unfocusedBorderColor = Color.Gray // Цвет рамки при отсутствии фокуса
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    Divider(Modifier.padding(vertical = 16.dp))
                    OutlinedTextField(
                        readOnly = true,
                        value = movie.synopsis,
                        onValueChange = { /*TODO*/ },
                        label = { Text("Synopsis", color = Color.LightGray) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedTextColor = Color.LightGray,
                            focusedBorderColor = Color.Gray, // Цвет рамки при фокусе
                            unfocusedBorderColor = Color.Gray // Цвет рамки при отсутствии фокуса
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    OutlinedTextField(
                        readOnly = true,
                        value = "${movie.director}",
                        onValueChange = { /*TODO*/ },
                        label = { Text("Director", color = Color.LightGray) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedTextColor = Color.LightGray,
                            focusedBorderColor = Color.Gray, // Цвет рамки при фокусе
                            unfocusedBorderColor = Color.Gray // Цвет рамки при отсутствии фокуса
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
        }
    }
}
fun genresToString(genres: List<Genre>): String{
    var genresString: String = ""
    genres.forEach { genre ->
        genresString += "${genre.name}, "
    }
    return genresString.dropLast(2)
}