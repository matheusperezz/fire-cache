package br.com.firecache.presentation.navigation.genres

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.firecache.presentation.genre.genreList.GenreListScreen

const val GENRE_LIST_ROUTE = "genre_list"

fun NavGraphBuilder.genreListScreen(navHostController: NavHostController) {
    composable(GENRE_LIST_ROUTE) {
        GenreListScreen(onAddGenreClick = {
            navHostController.navigateToCreateGenre()
        })
    }
}

fun NavController.navigateToGenreList() {
    navigate(GENRE_LIST_ROUTE)
}