package com.example.watchlinkapp.Movie.ComposeUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watchlinkapp.Movie.Model.Movie
import com.example.watchlinkapp.Movie.Model.getMovies
import com.example.watchlinkapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieView(id: Int) {
    val movie = getMovies().find { it.id == id }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
    ) {
        movie?.let {
            LazyColumn(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                item {
                    Image(
                        painter = painterResource(id = movie.imageResourceId),
                        contentDescription = null, // Укажите соответствующее описание
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
                            value = "${movie.genre}",
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
