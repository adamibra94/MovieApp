package com.example.movieapp.modules

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {

    var movies = getMovies().toMutableStateList()
    val allMovies: List<Movie>
        get() = movies

    private var favourite = mutableListOf<Movie>().toMutableStateList()
    val favoriteMovies: List<Movie>
        get() = favourite

    private var addMovie: Movie = Movie("", "", "", listOf(), "", "", "", listOf(), 0.0f)

    var isDisabled: MutableState<Boolean> = mutableStateOf(false)

    var title = mutableStateOf(addMovie.title)
    var errorTitle: MutableState<Boolean> = mutableStateOf(false)

    val year = mutableStateOf(addMovie.year)
    var errorYear: MutableState<Boolean> = mutableStateOf(false)

    var director = mutableStateOf(addMovie.director)
    var errorDirector: MutableState<Boolean> = mutableStateOf(false)

    var actors = mutableStateOf(addMovie.actors)
    var errorActors: MutableState<Boolean> = mutableStateOf(false)

    var plot = mutableStateOf(addMovie.plot)
    var errorPlot: MutableState<Boolean> = mutableStateOf(false)

    var rating = mutableStateOf(addMovie.rating.toString().replace("0.0", ""))
    var errorRating: MutableState<Boolean> = mutableStateOf(false)

    var genreItems = mutableStateOf(
        Genre.values().map { genre ->
            ListItemSelectable(
                title = genre.toString(),
                isSelected = false
            )
        }
    )
    var genreError: MutableState<Boolean> = mutableStateOf(false)

    fun validation() {
        validateTitle()
        validateYear()
        validateDirector()
        validateActors()
        validatePlot()
        validateRating()
        validateGenres()
    }

    fun addMovie(
        title: String,
        year: String,
        director: String,
        genres: List<Genre>,
        actors: String,
        plot: String,
        rating: String
    ) {
        val newMovie = Movie(
            id = "$title + $year + $director+ $genres + $actors",
            title = title,
            year = year,
            director = director,
            genre = genres,
            actors = actors,
            plot = plot,
            rating = rating.toFloat(), images = listOf(
                "https://images-na.ssl-images-amazon.com/images/M/MV5BNzM2MDk3MTcyMV5BMl5BanBnXkFtZTcwNjg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BMTMwNTg5MzMwMV5BMl5BanBnXkFtZTcwMzA2NTIyMw@@._V1_SX1777_CR0,0,1777,937_AL_.jpg",
                "https://images-na.ssl-images-amazon.com/images/M/MV5BMTExMDk1MDE4NzVeQTJeQWpwZ15BbWU4MDM4NDM0ODAx._V1_SX1500_CR0,0,1500,999_AL_.jpg",)
        )
        movies.add(newMovie)

    }


    fun toggleFavorite(movie: Movie) {
        movies.find { it.id == movie.id }?.let { task ->
            task.isFavorite = !task.isFavorite
            if (task.isFavorite) {
                favourite.add(movie)
            } else {
                favourite.remove(movie)
            }
        }
    }
    fun validateTitle() {
        errorTitle.value = title.value.isEmpty()
        addbuttonenable()

    }

    fun validateYear() {
        errorYear.value = year.value.isEmpty()
        addbuttonenable()
    }

    fun validateDirector() {
        errorDirector.value = director.value.isEmpty()
        addbuttonenable()
    }

    fun validateActors() {
        errorActors.value = actors.value.isEmpty()
        addbuttonenable()
    }

    fun validatePlot() {
        errorPlot.value = plot.value.isEmpty()
        addbuttonenable()
    }

    fun validateRating() {
        try {
            rating.value.toFloat()
            errorRating.value = false
        } catch (e: java.lang.Exception) {
            errorRating.value = true
        } finally {
            addbuttonenable()
        }
    }

    fun validateGenres() {
        genreError.value = true
        genreItems.value.forEach genres@{
            if (it.isSelected) {
                genreError.value = false
                return@genres
            }
        }
        addbuttonenable()
    }

    private fun addbuttonenable() {
        isDisabled.value =
            (errorTitle.value.not()
                    && errorYear.value.not()
                    && errorDirector.value.not()
                    && errorActors.value.not()
                    && errorPlot.value.not()
                    && errorRating.value.not()
                    && genreError.value.not()
                    )
    }
}