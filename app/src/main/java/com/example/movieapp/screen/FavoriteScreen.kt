package com.example.movieapp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieapp.navigation.Screen
import com.example.movieapp.widgets.SimpleTopAppBar
import com.example.movieapp.modules.ViewModel
import com.example.movieapp.widgets.MovieRow


@Composable
fun FavoriteScreen(navController: NavController, viewModel: ViewModel) {
    Scaffold(topBar = {
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
            Text(text = "Favorites")
        }
    }){ padding ->

        Column(modifier = Modifier.padding(padding)) {
            LazyColumn {
                items(viewModel.favoriteMovies){ movie ->
                    MovieRow(
                        movie = movie,
                        onItemClick = { movieId ->
                            navController.navigate(route = Screen.DetailScreen.withId(movieId))
                        },
                        onFavClick = {
                        viewModel.toggleFavorite(it)
                        })
                }
            }
        }
    }
}