package com.example.movieapp.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.modules.Genre
import com.example.movieapp.modules.ViewModel
import com.example.movieapp.widgets.SimpleTopAppBar



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContents(modifier: Modifier = Modifier, navController: NavController, viewModel: ViewModel) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            TextInputField(
                text = viewModel.title,
                errorState = viewModel.errorTitle,
                label = R.string.enter_movie_title
            ) { viewModel.validateTitle() }

            TextInputField(
                text = viewModel.year,
                errorState = viewModel.errorYear,
                label = R.string.enter_movie_year
            ) { viewModel.validateYear() }

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6)

            ErrorMessage(value = viewModel.genreError.value)

            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp),
                rows = GridCells.Fixed(3)){
                items(viewModel.genreItems.value) { genreItem ->
                    Chip(
                        modifier = Modifier.padding(2.dp),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = if (genreItem.isSelected)
                                colorResource(id = R.color.purple_200)
                            else
                                colorResource(id = R.color.white)
                        ),
                        onClick = {
                            viewModel.genreItems.value = viewModel.genreItems.value.map {
                                if (it.title == genreItem.title) {
                                    genreItem.copy(isSelected = !genreItem.isSelected)
                                } else {
                                    it
                                }
                            }
                            viewModel.validateGenres()
                        }
                    ) {
                        Text(text = genreItem.title)
                    }
                }
            }

            TextInputField(
                text = viewModel.director,
                errorState = viewModel.errorDirector,
                label = R.string.enter_director
            ) { viewModel.validateDirector() }

            TextInputField(
                text = viewModel.actors,
                errorState = viewModel.errorActors,
                label = R.string.enter_actors
            ) { viewModel.validateActors() }

            TextInputField(
                text = viewModel.plot,
                errorState = viewModel.errorPlot,
                label = R.string.enter_plot
            ) { viewModel.validatePlot() }

            TextInputField(
                viewModel.rating,
                viewModel.errorRating,
                R.string.enter_rating
            ) { viewModel.validateRating() }

            Button(
                enabled = viewModel.isDisabled.value,
                onClick = {
                    val genreList: MutableList<Genre> = mutableListOf()
                    viewModel.genreItems.value.filter { it.isSelected }.forEach { genreList.add(Genre.valueOf(it.title)) }

                    viewModel.addMovie(
                        viewModel.title.value,
                        viewModel.year.value,
                        viewModel.director.value,
                        genreList,
                        viewModel.actors.value,
                        viewModel.plot.value,
                        viewModel.rating.value
                    )
                    navController.popBackStack()
                }) {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}

@Composable
 fun TextInputField(text: MutableState<String>, errorState: MutableState<Boolean>, label: Int, validateMethod: () -> Unit) {
    OutlinedTextField(
        value = text.value,
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {
            text.value = it
            validateMethod()
        },
        label = { Text(stringResource(id = label)) },
        isError = errorState.value
    )
    ErrorMessage(value = errorState.value)
}

@Composable
 fun ErrorMessage(value: Boolean) {
    if (value) {
        Text(
            text = "Please fill the field",
            color = Color.Red,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
fun AddMovieScreen(navController: NavController, viewModel: ViewModel){
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                Text(text = stringResource(id = R.string.add_movie))
            }
        },
    ) { padding ->
        MainContents(Modifier.padding(padding), navController, viewModel = viewModel)
    }
    viewModel.validation()
}
