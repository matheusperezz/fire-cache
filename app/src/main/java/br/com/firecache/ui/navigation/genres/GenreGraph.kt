package br.com.firecache.ui.navigation.genres

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation

internal const val GENRE_GRAPH_ROUTE = "genre_graph"

fun NavGraphBuilder.genreGraph(navHostController: NavHostController) {
    navigation(
        route = GENRE_GRAPH_ROUTE,
        startDestination = GENRE_LIST_ROUTE
    ) {
        genreListScreen(navHostController)
        createGenreScreen(navHostController)
    }
}