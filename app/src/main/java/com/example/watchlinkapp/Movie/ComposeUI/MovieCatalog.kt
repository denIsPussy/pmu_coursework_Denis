package com.example.watchlinkapp.Movie.ComposeUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.watchlinkapp.Movie.Model.Movie
import com.example.watchlinkapp.Movie.Model.getMovies
import com.example.watchlinkapp.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@Composable
fun MovieCatalog(navController: NavController) {
    val movies = getMovies()

    LazyColumn(
        modifier = Modifier
            .background(colorResource(id = R.color.backgroundColor))
    ) {
        item {
            BannerView()
        }
        val genres = movies.map { it.genre }.distinct()
        genres.forEach { genre ->
            item {
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(180.dp)
                ) {
                    Text(
                        text = genre,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .wrapContentWidth()
                    )
                }
            }
            item {
                LazyRow(
                    modifier = Modifier
                        //.background(Color.Red)
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                ) {
                    val moviesByGenre = movies.filter { it.genre == genre }
                    items(moviesByGenre.size) { index ->
                        val movie = moviesByGenre[index]
                        val movieId = Screen.MovieView.route.replace("{id}", movie.id.toString())
                        Card(
                            modifier = Modifier
                                .padding(
                                    start = 8.dp,
                                    end = 2.dp,
                                    top = 5.dp
                                )//.background(Color.Green)
                                //.widthIn(min = 150.dp, max = 200.dp)
                                .width(110.dp)
                                .fillMaxWidth()
                                .clickable { navController.navigate(movieId) },
                            shape = RoundedCornerShape(5.dp)
                        ) {
                            Image(
                                painter = painterResource(id = movie.imageResourceId),
                                contentDescription = "image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    //.clip((5.dp))
                            )
                        }
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerView() {
    val images = listOf(
        painterResource(R.drawable.banner_image1),
        painterResource(R.drawable.banner_image2),
        painterResource(R.drawable.banner_image3)
    )
    var currentPage by remember { mutableStateOf(0) }
    LaunchedEffect(currentPage) {
        while (true) {
            delay(3000)
            currentPage = (currentPage + 1) % images.size
        }
    }
     val pagerState = rememberPagerState(
         pageCount = images.size,
         initialPage = currentPage,
         infiniteLoop = true)

     HorizontalPager(state = pagerState) { page ->
         Image(
             painter = images[page],
             contentDescription = null,
             modifier = Modifier.fillMaxWidth(),
             contentScale = ContentScale.FillWidth
         )
     }
}