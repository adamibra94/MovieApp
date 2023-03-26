package com.example.movieapp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieapp.Modules.getMovies
import com.example.movieapp.widgets.MovieList
import com.example.movieapp.widgets.SimpleAppBar


@Composable
fun FavoriteScreen(navController: NavController){
    val movies = getMovies()
    val favorites = listOf( movies[2], movies[4], movies[6],movies[3] )
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column {
            SimpleAppBar(title = "Favorites", navController = navController)
            MovieList(movies = favorites, navController = navController)
        }
    }
}
