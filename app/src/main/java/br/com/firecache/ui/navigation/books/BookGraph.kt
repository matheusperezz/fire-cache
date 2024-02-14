package br.com.firecache.ui.navigation.books

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation

internal const val BOOK_GRAPH_ROUTE = "book_graph"

fun NavGraphBuilder.bookGraph(navHostController: NavHostController) {
    navigation(
        route = BOOK_GRAPH_ROUTE,
        startDestination = BOOK_LIST_ROUTE
    ) {
        bookListScreen(navHostController)
        createBookScreen(navHostController)
        bookDetailsScreen(navHostController)
        updateBookScreen(navHostController)
    }
}