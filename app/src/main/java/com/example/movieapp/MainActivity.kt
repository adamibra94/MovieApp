package com.example.movieapp
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.ui.theme.MovieAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Column {
                        MyScreen()
                        MovieList()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieList(movies: List<Movie> = getMovies()) {
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie)
        }
    }
}

@Composable
fun MovieRow(movie: Movie) {
    Card(
        Modifier.fillMaxWidth().padding(5.dp),
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
                    painter = rememberAsyncImagePainter(movie.images[2]),
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Box(Modifier.fillMaxWidth().padding(10.dp), contentAlignment = Alignment.TopEnd) {
                    Icon(
                        Icons.Default.FavoriteBorder,
                        contentDescription = "Add to Favourite",
                        tint = androidx.compose.ui.graphics.Color.White
                    )
                }
            }

            Row(Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(movie.title, style = MaterialTheme.typography.h6)

                var isExpanded by remember { mutableStateOf(false) }

                Box(Modifier.clickable { isExpanded = !isExpanded }) {
                    Icon(
                        if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "Show details"
                    )
                }

                AnimatedVisibility(visible = isExpanded) {
                    Column(Modifier.padding(vertical = 30.dp)) {
                        MovieDetailsRow("Genre", movie.genre)
                        MovieDetailsRow("Released", movie.year)
                        MovieDetailsRow("Director", movie.director)
                        MovieDetailsRow("Actors", movie.actors)
                        MovieDetailsRow("Rating", movie.rating)
                        Divider(thickness = 1.dp)
                        MovieDetailsRow("Plot", movie.plot)
                    }
                }
            }
        }
    }
}

@Composable
fun MovieDetailsRow(label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("$label: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(value, style = MaterialTheme.typography.subtitle2)
    }
}

@Composable
fun MyScreen() {
    var menuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {
            Row {
                IconButton(onClick = { menuExpanded = !menuExpanded }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Favorites")
                }
                Spacer(modifier = Modifier.width(8.dp))
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false },
                ) {
                    DropdownMenuItem(onClick = { /* Handle Favorites click */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(text = "Favorites")
                    }
                }
            }
        }
    )
}




