package br.com.firecache.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.firecache.presentation.navigation.BottomBarNavigation
import br.com.firecache.presentation.navigation.FireNavHost
import br.com.firecache.presentation.navigation.books.ADD_BOOK_ROUTE
import br.com.firecache.presentation.navigation.books.BOOK_ARGUMENT_KEY_ID
import br.com.firecache.presentation.navigation.books.BOOK_DETAILS_ROUTE

@Composable
fun App(
    navHostController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isShowBottomBar: Boolean = when (currentDestination?.route) {
        "$BOOK_DETAILS_ROUTE/{$BOOK_ARGUMENT_KEY_ID}", ADD_BOOK_ROUTE -> false
        else -> true
    }

    AppStateless(
        navController = navHostController,
        currentDestination = currentDestination,
        isShowBottomBar = isShowBottomBar
    ) {
        FireNavHost(navHostController = navHostController)
    }

}

@Composable
fun AppStateless(
    navController: NavHostController,
    currentDestination: NavDestination?,
    isShowBottomBar: Boolean = false,
    isShowTopBar: Boolean = false,
    isShowFab: Boolean = false,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            if (isShowBottomBar){
                BottomBarNavigation(
                    currentDestinantion = currentDestination,
                    navController = navController
                )
            }
        },
    ) {
        Box(modifier = Modifier.padding(it).fillMaxSize()) {
            content()
        }
    }
}