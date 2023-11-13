package com.example.watchlinkapp.ComposeUI


import android.graphics.BitmapFactory
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.watchlinkapp.ComposeUI.Navigation.Screen
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TextField
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.example.watchlinkapp.Database.AppDatabase
import com.example.watchlinkapp.Entities.Model.Movie
import com.example.watchlinkapp.Entities.Model.MovieWithGenres
import com.example.watchlinkapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(navController: NavController) {
    val context = LocalContext.current
    val (movies, setMovies) = remember { mutableStateOf(emptyList<Movie>()) }
    var filtredMovies by remember { mutableStateOf(movies) }
    var searchText by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val data = AppDatabase.getInstance(context).movieDao().getAll()
            setMovies(data)
            filtredMovies = data
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            movies = movies,
            onMoviesChanged = { updatedMovies -> filtredMovies = updatedMovies },
            searchDisplay = searchText,
            onSearchDisplayChanged = { text ->
                searchText = text
            },
            onSearchDisplayClosed = { filtredMovies = movies },
            modifier = Modifier.fillMaxWidth(),
            tint = Color.White
        )

        LazyColumn(
            modifier = Modifier
                .background(colorResource(id = R.color.backgroundColor))
                .padding(top = 8.dp)
        ) {
            item {
                Text(
                    text = "Возможно тебя заинтересует",
                    color = colorResource(id = R.color.textColor),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 16.dp, bottom = 12.dp)
                )
            }
            val chunkedMovies = filtredMovies.chunked(3)
            chunkedMovies.forEach { movieChunk ->
                item {
                    Row(
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        movieChunk.forEach { movie ->
                            val movieId =
                                Screen.MovieView.route.replace("{id}", movie.movieId.toString())
                            Card(
                                modifier = Modifier
                                    .padding(
                                        start = 8.dp,
                                        end = 2.dp,
                                        top = 5.dp
                                    )
                                    .width(110.dp)
                                    .fillMaxWidth()
                                    .clickable { navController.navigate(movieId) },
                                shape = RoundedCornerShape(5.dp)
                            ) {
                                val decodedBitmap = BitmapFactory.decodeByteArray(movie.image, 0, movie.image!!.size)
                                val imageBitmap = decodedBitmap.asImageBitmap()
                                Image(
                                    bitmap = imageBitmap,
                                    contentDescription = "image",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.wrapContentWidth().wrapContentHeight()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun SearchBar(
    movies: List<Movie>,
    onMoviesChanged: (List<Movie>) -> Unit,
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onPrimary,
) {
    val focusManager = LocalFocusManager.current

    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    DisposableEffect(key1 = onBackPressedDispatcher) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                focusManager.clearFocus()
                onSearchDisplayClosed()
            }
        }
        onBackPressedDispatcher?.addCallback(callback)

        onDispose {
            callback.remove()
        }
    }

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(searchDisplay, TextRange(searchDisplay.length)))
    }

    TextField(
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = colorResource(id = R.color.backgroundNavBarColor),
            focusedContainerColor = colorResource(id = R.color.backgroundNavBarColor),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onSearchDisplayChanged(it.text)
            val updatedMovies = movies.filter { movie -> movie.title.startsWith(it.text, ignoreCase = true) }
            onMoviesChanged(updatedMovies)
        },
        trailingIcon = {
            // Ваш иконка поиска
        },
        modifier = modifier,
        label = {
            Text(text = "Поиск...", color = tint)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                onSearchDisplayClosed()
            }
        ),
    )
}