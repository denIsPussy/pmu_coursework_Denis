package com.example.watchlinkapp.ComposeUI.Navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.watchlinkapp.R

enum class Screen (
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector = Icons.Filled.Favorite,
    val showInBottomBar: Boolean = true,
    val isAuthenticated: Boolean = true,
){
    MovieCatalog("movie-catalog", R.string.movie_catalog_title, Icons.Filled.Home),
    Profile("profile", R.string.profile_title, Icons.Filled.AccountCircle),
    Search("search", R.string.search_title, Icons.Filled.Search),
    Signup("signup", R.string.signup_title, Icons.Filled.Search, showInBottomBar = false, isAuthenticated = false),
    Login("login", R.string.login_title, Icons.Filled.Search, showInBottomBar = false, isAuthenticated = false),
    MovieView("movie-view/{id}", R.string.movie_view_title, Icons.Filled.List, showInBottomBar = false);
    companion object {
        val bottomBarItems = listOf(
            MovieCatalog,
            Profile,
            Search
        )

        fun getItem(route: String): Screen? {
            val findRoute = route.split("/").first()
            return values().find { value -> value.route.startsWith(findRoute) }
        }
    }
}