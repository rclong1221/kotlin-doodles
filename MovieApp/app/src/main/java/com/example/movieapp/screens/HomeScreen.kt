package com.example.movieapp.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController) {
    val movies = getMovies()
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Magenta,
                elevation = 5.dp
            ) {
                Text(text = "Movies")
            }
        },
    ) {
        MainContent(navController = navController, movies = movies)
    }
}

@Composable
fun MainContent(navController: NavController, movies: List<Movie>) {
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn {
            items(items = movies) {
                MovieRow(movie = it) {
                        m -> navController.navigate(
                    MovieScreens.DetailsScreen.name + "/$m")
                }
            }
        }
    }
}