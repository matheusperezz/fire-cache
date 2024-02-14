package br.com.firecache.ui.navigation.books

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.firecache.ui.book.createBook.CreateBookScreen

const val ADD_BOOK_ROUTE = "add_book"

fun NavGraphBuilder.createBookScreen(navHostController: NavHostController) {
    composable(
        route = "$ADD_BOOK_ROUTE/{$BOOK_ARGUMENT_KEY_ID}",
        arguments = listOf(
            navArgument(BOOK_ARGUMENT_KEY_ID) {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }
        )
    ) { navBackStackEntry ->
        val bookId: String? = navBackStackEntry.arguments?.getString(BOOK_ARGUMENT_KEY_ID)
        CreateBookScreen(onSaveClick = { navHostController.popBackStack() }, bookId = bookId)
    }
}

fun NavController.navigateToAddBook(bookId: String?) {
    navigate("$ADD_BOOK_ROUTE/$bookId")
}