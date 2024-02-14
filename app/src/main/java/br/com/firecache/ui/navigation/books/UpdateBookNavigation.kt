package br.com.firecache.ui.navigation.books

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.firecache.ui.book.updateBook.UpdateBookScreen

const val UPDATE_BOOK_ROUTE = "update_book"

fun NavGraphBuilder.updateBookScreen(navHostController: NavHostController) {
    composable(
        route = "$UPDATE_BOOK_ROUTE/{$BOOK_ARGUMENT_KEY_ID}",
        arguments = listOf(
            navArgument(BOOK_ARGUMENT_KEY_ID) { type = NavType.StringType }
        )
    ) { navBackStackEntry ->
        val bookId = navBackStackEntry.arguments?.getString(BOOK_ARGUMENT_KEY_ID)
        bookId?.let {
            UpdateBookScreen(
                bookId = it,
            )
        }
    }
}

fun NavController.navigateToUpdateBook(bookId: String) {
    navigate("$UPDATE_BOOK_ROUTE/$bookId")
}