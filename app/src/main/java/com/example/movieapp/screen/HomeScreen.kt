package com.example.movieapp.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieapp.modules.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.navigation.Screen
import com.example.movieapp.widgets.HomeTopAppBar
import com.example.movieapp.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController = rememberNavController(), viewModel: ViewModel
) {
    Scaffold(topBar = {
        HomeTopAppBar(
            title = "Home",
            menuContent = {
                DropdownMenuItem(onClick = { navController.navigate(Screen.AddMovieScreen.route) }) {
                    Row {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Add Movie", modifier = Modifier.padding(4.dp))
                        Text(text = "Add Movie", modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp))
                    }
                }
                DropdownMenuItem(onClick = { navController.navigate(Screen.FavoriteScreen.route) }) {
                    Row {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites", modifier = Modifier.padding(4.dp))
                        Text(text = "Favorites", modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp))
                    }
                }
            }
        )
    }) { padding ->
        MainContent (modifier = Modifier.padding(padding), navController = navController, viewModel = viewModel)
    }
}

@Composable
fun MainContent(modifier: Modifier, navController: NavController, viewModel: ViewModel) {
    MovieList(
        modifier = modifier,
        navController = navController,
        viewModel = viewModel,
    )
}

@Composable
fun MovieList(modifier: Modifier = Modifier, navController: NavController, viewModel: ViewModel ) {
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(viewModel.allMovies) { movie ->
            MovieRow(
                movie = movie,
                onItemClick = { movieId ->
                    navController.navigate(Screen.DetailScreen.withId(movieId))
                }
            ) { movieId ->
                viewModel.toggleFavorite(movieId)
            }
        }
    }
}









