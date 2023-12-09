package com.example.watchlinkapp.ComposeUI.Movie

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.watchlinkapp.ComposeUI.AppViewModelProvider
import com.example.watchlinkapp.ComposeUI.Navigation.Screen
import com.example.watchlinkapp.Database.AppDatabase
import com.example.watchlinkapp.Entities.Model.Genre.GenresWithMovies
import com.example.watchlinkapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieCatalog(
    navController: NavController,
    viewModel: MovieCatalogViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val genresListUiState = viewModel.genreListUiState.collectAsLazyPagingItems()
    val moviesListUiState = viewModel.movieListUiState.collectAsLazyPagingItems()

    val refreshScope = rememberCoroutineScope()
    var refreshingGenre by remember { mutableStateOf(false) }
    fun refreshGenre() = refreshScope.launch {
        refreshingGenre = true
        genresListUiState.refresh()
        refreshingGenre = false
    }
    val stateGenre = rememberPullRefreshState(refreshingGenre, ::refreshGenre)

    var refreshingMovie by remember { mutableStateOf(false) }
    fun refreshMovie() = refreshScope.launch {
        refreshingMovie = true
        moviesListUiState.refresh()
        refreshingMovie = false
    }
    val stateMovie = rememberPullRefreshState(refreshingMovie, ::refreshMovie)

    LazyColumn(
        modifier = Modifier.pullRefresh(stateGenre)
            .background(colorResource(id = R.color.backgroundColor))
    ) {
        item {
            //BannerView()
        }
        items(count = genresListUiState.itemCount, key = genresListUiState.itemKey(), contentType = genresListUiState.itemContentType()) { index ->
            val genre = genresListUiState[index]
            Box(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .width(180.dp)
            ) {
                genre?.let {
                    Text(
                        text = it.name,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .wrapContentWidth()
                    )
                }
            }
            LazyRow(
                modifier = Modifier
                    .pullRefresh(stateMovie)
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            ) {
                items(count = moviesListUiState.itemCount, key = moviesListUiState.itemKey(), contentType = moviesListUiState.itemContentType()) { index1 ->
                    val movie = moviesListUiState[index1]
                    val movieId = Screen.MovieView.route.replace("{id}", movie?.movieId.toString())
                    movie?.let {
                        if (viewModel.containsMovieInGenre(movie.movieId!!, genre!!.genreId!!)){
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
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
//                                        .wrapContentWidth().wrapContentHeight()
                                )
                            }
                        }
                    }
                }
            }
        }
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