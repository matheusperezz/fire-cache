package br.com.firecache.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.firecache.presentation.navigation.books.BOOK_GRAPH_ROUTE
import br.com.firecache.presentation.navigation.books.bookGraph
import br.com.firecache.presentation.navigation.genres.genreGraph

@Composable
fun FireNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = BOOK_GRAPH_ROUTE
    ) {
        bookGraph(navHostController)
        genreGraph(navHostController)
    }
}