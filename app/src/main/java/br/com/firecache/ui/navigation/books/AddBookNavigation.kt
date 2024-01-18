package br.com.firecache.ui.navigation.books

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.firecache.ui.screens.AddBookScreen

const val ADD_BOOK_ROUTE = "add_book"

fun NavGraphBuilder.createBookScreen(navHostController: NavHostController) {
    composable(ADD_BOOK_ROUTE) {
        AddBookScreen()
    }
}

fun NavController.navigateToAddBook() {
    navigate(ADD_BOOK_ROUTE)
}