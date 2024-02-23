package br.com.firecache.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import br.com.firecache.R
import br.com.firecache.presentation.navigation.books.BOOK_GRAPH_ROUTE
import br.com.firecache.presentation.navigation.genres.GENRE_GRAPH_ROUTE

@Composable
fun BottomBarNavigation(
    currentDestinantion: NavDestination?,
    navController: NavHostController
) {
    NavigationBar {
        bottomNavigationItems.forEach { screen ->
            val isSelected = currentDestinantion?.hierarchy?.any { it.route == screen.route } == true
            NavigationBarItem(
                icon = {
                    if (isSelected) screen.iconSelected() else screen.iconDiselected()
                },
                label = { Text(text = screen.label) },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

sealed class BottomAppBarItem(
    val label: String,
    val iconSelected: @Composable () -> Unit,
    val iconDiselected: @Composable () -> Unit,
    val route: String
) {
    object Books : BottomAppBarItem(
        label = "Livros",
        iconSelected = { Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_book), contentDescription = null) },
        iconDiselected = { Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_book_outlined), contentDescription = null) },
        route = BOOK_GRAPH_ROUTE
    )

    object Genre : BottomAppBarItem(
        label = "Gêneros",
        iconSelected = { Icon(imageVector = Icons.Filled.List, contentDescription = null) },
        iconDiselected = { Icon(imageVector = Icons.Outlined.List, contentDescription = null) },
        route = GENRE_GRAPH_ROUTE
    )
}

val bottomNavigationItems = listOf(
    BottomAppBarItem.Books,
    BottomAppBarItem.Genre
)