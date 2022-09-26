package com.example.movieapp.screens

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.MovieRow
import com.example.movieapp.navigation.MovieScreens

@Composable
fun HomeScreen(navController: NavController) {
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
        MainContent(navController = navController)
    }
}

@Composable
fun MainContent(navController: NavController, movies: List<String> = listOf(
    "Avatar",
    "300",
    "Larry Botter",
    "Life",
    "Avatar2",
    "3002",
    "Larry Botter2",
    "Life2",
    "Avatar3",
    "3003",
    "Larry Botter3",
    "Life3",
    "Avatar4",
    "3004",
    "Larry Botter4",
    "Life4",
    "Avatar5",
    "3005",
    "Larry Botter5",
    "Life5"
)) {
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