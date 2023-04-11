package com.example.movieapp.navigation

////DONE WITH KENAN HUSIC, MUHAMMAD TAHA IMRAN

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.movieapp.modules.ViewModel
import com.example.movieapp.ui.theme.MovieAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: ViewModel by viewModels()
        viewModel.movies
        setContent {
            MovieAppTheme {
                Navigation()
                }

            }
        }
    }







