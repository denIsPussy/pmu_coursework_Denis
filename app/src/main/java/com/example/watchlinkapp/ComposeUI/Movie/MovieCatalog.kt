package com.example.watchlinkapp.ComposeUI.Movie

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.watchlinkapp.ComposeUI.AppViewModelProvider
import com.example.watchlinkapp.ComposeUI.Navigation.Screen
import com.example.watchlinkapp.Database.AppDatabase
import com.example.watchlinkapp.Entities.Model.Genre.GenresWithMovies
import com.example.watchlinkapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun MovieCatalog(
    navController: NavController,
    viewModel: MovieCatalogViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    //val context = LocalContext.current
    //val genresWithMovies = remember { mutableStateListOf<GenresWithMovies>() }
//    LaunchedEffect(Unit) {
//        withContext(Dispatchers.IO) {
//            AppDatabase.getInstance(context).genreDao().getGenresWithMovies().collect { data ->
//                genresWithMovies.clear()
//                genresWithMovies.addAll(data)
//            }
//        }
//    }
//    try {
//        val viewModel: MovieCatalogViewModel = viewModel(factory = AppViewModelProvider.Factory)
//        // Далее продолжайте использовать viewModel
//    } catch (e: Exception) {
//        Log.e("MovieCatalog", "Ошибка инициализации ViewModel: ${e.message}")
//    }
    val genresListUiState by viewModel.movieListUiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .background(colorResource(id = R.color.backgroundColor))
    ) {
        item {
            //BannerView()
        }
        items(items = genresListUiState.movieList.filter { genresWithMovies -> genresWithMovies.movies.isNotEmpty() }, key = { it.genre.genreId!! }) { genreWithMovies ->
            //this@LazyColumn.item {
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(180.dp)
                ) {
                    Text(
                        text = genreWithMovies.genre.name,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .wrapContentWidth()
                    )
                }
            //}
            //this@LazyColumn.item {
                LazyRow(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                ) {
                    val moviesByGenre = genreWithMovies.movies
                    items(items = moviesByGenre, key = { it.movieId!! }) { movie ->
                    //moviesByGenre.forEach{ movie ->
                        val movieId = Screen.MovieView.route.replace("{id}", movie.movieId.toString())
                       // this@LazyRow.item {
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
                                    bitmap  = imageBitmap,
                                    contentDescription = "image",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .wrapContentWidth().wrapContentHeight()
                                )
                            }
                        //}

                    }
                }
            //}
        }
//        genresListUiState.movieList.filter { genresWithMovies -> genresWithMovies.movies.isNotEmpty() }.forEach { genreWithMovies ->
//
//        }
    }
}
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun BannerView() {
//    val images = listOf(
//        painterResource(R.drawable.banner_image1),
//        painterResource(R.drawable.banner_image2),
//        painterResource(R.drawable.banner_image3)
//    )
//    var currentPage by remember { mutableStateOf(0) }
//    LaunchedEffect(currentPage) {
//        while (true) {
//            delay(3000)
//            currentPage = (currentPage + 1) % images.size
//        }
//    }
//     val pagerState = rememberPagerState(
//         pageCount = images.size,
//         initialPage = currentPage,
//         infiniteLoop = true)
//
//     HorizontalPager(state = pagerState) { page ->
//         Image(
//             painter = images[page],
//             contentDescription = null,
//             modifier = Modifier.fillMaxWidth(),
//             contentScale = ContentScale.FillWidth
//         )
//     }
//}