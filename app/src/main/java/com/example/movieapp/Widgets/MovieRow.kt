package com.example.movieapp.widgets


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.Modules.Movie
import com.example.movieapp.Modules.getMovies
import com.example.movieapp.navigation.Screen


@Composable
fun MovieList(navController: NavController = rememberNavController(),
              movies: List<Movie> = getMovies()
) {
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie = movie) { movieId ->
                navController.navigate(Screen.DetailScreen.route + "/$movieId")
            }
        }
    }
}
@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(movie.images[1]),
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to Favourite",
                        tint = Color.White
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(movie.title, style = MaterialTheme.typography.h6)
                var isExpanded by remember { mutableStateOf(false) }
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    Row(modifier = Modifier.clickable { isExpanded = !isExpanded }
                    ) {
                        Icon(
                            imageVector = if (isExpanded) Icons.Default.KeyboardArrowDown
                            else Icons.Default.KeyboardArrowUp,
                            contentDescription = "Show details",
                        )

                    }
                }
                AnimatedVisibility(visible = isExpanded) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween)
                    {
                        Column(modifier = Modifier.padding(5.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp))
                        {
                            Row {
                                Text(
                                    text = "Genre : ",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = movie.genre, style = MaterialTheme.typography.subtitle2)
                            }
                            Row {
                                Text(
                                    text = "Released : ",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = movie.year, style = MaterialTheme.typography.subtitle2)
                            }
                            Row {
                                Text(
                                    text = "Director : ",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = movie.director, style = MaterialTheme.typography.subtitle2)
                            }
                            Row {
                                Text(
                                    text = "Actors : ",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = movie.actors, style = MaterialTheme.typography.subtitle2)
                            }
                            Row {
                                Text(
                                    text = "Rating : ",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = movie.rating, style = MaterialTheme.typography.subtitle2)
                            }
                            Divider(
                                thickness = 3.dp
                            )
                            Row {
                                Text(
                                    text = "Plot : ",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = movie.plot, style = MaterialTheme.typography.subtitle2)
                            }
                        }
                    }
                }
            }
        }
    }
}