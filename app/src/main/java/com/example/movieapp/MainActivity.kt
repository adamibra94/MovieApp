package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.movieapp.navigation.Navigation

import com.example.movieapp.ui.theme.MovieAppTheme

// Done with Muhammad Taha Imran,Kenan Husic

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                Navigation()
            }

        }
    }
}







