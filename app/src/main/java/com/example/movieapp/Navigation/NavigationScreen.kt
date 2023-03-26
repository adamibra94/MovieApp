package com.example.movieapp.navigation

sealed class Screen (val route: String) {
    object HomeScreen: Screen("home")
    object DetailScreen: Screen("details")
    object FavoritesScreen: Screen("favorites")
}