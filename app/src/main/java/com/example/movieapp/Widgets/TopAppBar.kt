package com.example.movieapp.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.navigation.Screen

@Composable
fun TopAppBar(navController: NavController) {
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
                    DropdownMenuItem(onClick = { menuExpanded = true; navController.navigate(Screen.FavoritesScreen.route)}) {
                        Icon(Icons.Filled.Favorite,contentDescription = null)
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(text = "Favorites")
                    }
                }
            }
        }
    )
}
