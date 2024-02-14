package br.com.firecache.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.firecache.ui.navigation.BottomBarNavigation
import br.com.firecache.ui.navigation.FireNavHost
import br.com.firecache.ui.navigation.books.ADD_BOOK_ROUTE
import br.com.firecache.ui.navigation.books.BOOK_DETAILS_ARGUMENT_KEY
import br.com.firecache.ui.navigation.books.BOOK_DETAILS_ROUTE

@Composable
fun App(
    navHostController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isShowBottomBar: Boolean = when (currentDestination?.route) {
        "$BOOK_DETAILS_ROUTE/{$BOOK_DETAILS_ARGUMENT_KEY}", ADD_BOOK_ROUTE -> false
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