package br.com.firecache.ui.navigation.books

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.firecache.ui.bookDetails.BookDetailsScreen
import br.com.firecache.ui.bookList.BookListScreen

const val BOOK_DETAILS_ROUTE = "book_details"
const val BOOK_DETAILS_ARGUMENT_KEY = "id"

fun NavGraphBuilder.bookDetailsScreen(navHostController: NavHostController) {
    composable(
        route = "$BOOK_DETAILS_ROUTE/{$BOOK_DETAILS_ARGUMENT_KEY}",
        arguments = listOf(
            navArgument(BOOK_DETAILS_ARGUMENT_KEY) { type = NavType.StringType }
        )
    ){ navBackStackEntry ->
        val bookId = navBackStackEntry.arguments?.getString(BOOK_DETAILS_ARGUMENT_KEY)
        bookId?.let { notNullBookId ->
            BookDetailsScreen(bookId = notNullBookId, navHostController = navHostController)
        }
    }
}

fun NavController.navigateToBookDetails(bookId: String) {
    navigate("$BOOK_DETAILS_ROUTE/$bookId")
}