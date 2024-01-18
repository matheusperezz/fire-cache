package br.com.firecache.ui.navigation.books

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.firecache.ui.screens.BookListScreen

const val BOOK_LIST_ROUTE = "book_list"

fun NavGraphBuilder.bookListScreen(navHostController: NavHostController) {
    composable(BOOK_LIST_ROUTE) {
        BookListScreen(
            onAddBookClick = { navHostController.navigateToAddBook() }
        )
    }
}

fun NavController.navigateToBookList() {
    navigate(BOOK_LIST_ROUTE)
}