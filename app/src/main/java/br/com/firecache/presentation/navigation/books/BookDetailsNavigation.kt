package br.com.firecache.presentation.navigation.books

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.firecache.presentation.book.bookDetails.BookDetailsScreen

const val BOOK_DETAILS_ROUTE = "book_details"
const val BOOK_ARGUMENT_KEY_ID = "id"

fun NavGraphBuilder.bookDetailsScreen(navHostController: NavHostController) {
    composable(
        route = "$BOOK_DETAILS_ROUTE/{$BOOK_ARGUMENT_KEY_ID}",
        arguments = listOf(
            navArgument(BOOK_ARGUMENT_KEY_ID) { type = NavType.StringType }
        )
    ){ navBackStackEntry ->
        val bookId = navBackStackEntry.arguments?.getString(BOOK_ARGUMENT_KEY_ID)
        bookId?.let { notNullBookId ->
            BookDetailsScreen(
                bookId = notNullBookId,
                navHostController = navHostController,
                onEditClick = {  }
            )
        }
    }
}

fun NavController.navigateToBookDetails(bookId: String) {
    navigate("$BOOK_DETAILS_ROUTE/$bookId")
}