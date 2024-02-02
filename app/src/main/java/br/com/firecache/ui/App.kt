package br.com.firecache.ui

import androidx.compose.foundation.layout.Box
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

@Composable
fun App(
    navHostController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    AppStateless(navController = navHostController, currentDestination = currentDestination) {
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
            BottomBarNavigation(
                currentDestinantion = currentDestination,
                navController = navController
            )
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            content()
        }
    }
}