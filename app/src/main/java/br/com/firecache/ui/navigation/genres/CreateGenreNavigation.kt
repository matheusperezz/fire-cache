package br.com.firecache.ui.navigation.genres

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.firecache.ui.createGenre.CreateGenreScreen

const val CREATE_GENRE_ROUTE = "create_genre"

fun NavGraphBuilder.createGenreScreen(navHostController: NavHostController) {
    composable(CREATE_GENRE_ROUTE) {
        CreateGenreScreen(
            onSavePopNavigation = {
                navHostController.popBackStack()
            }
        )
    }
}

fun NavController.navigateToCreateGenre() {
     navigate(CREATE_GENRE_ROUTE)
}