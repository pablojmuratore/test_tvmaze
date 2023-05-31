package com.pablojmuratore.testtvmaze.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pablojmuratore.testtvmaze.login.navigation.LoginNavigation
import com.pablojmuratore.testtvmaze.login.navigation.loginNavGraph
import com.pablojmuratore.testtvmaze.shows.navigation.ShowsNavigation
import com.pablojmuratore.testtvmaze.shows.navigation.showsNavGraph

@Composable
fun MainNavHost(
    navController: NavHostController,
    onUseFingerprint: () -> Unit = {},
    isFingerprintAuthenticated: Boolean = false
) {
    val startDestination = if (isFingerprintAuthenticated) ShowsNavigation.NavGraphs.SHOWS_NAV_GRAPH.route else LoginNavigation.NavGraphs.LOGIN_NAV_GRAPH.route

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        loginNavGraph(
            navController,
            onLoginPassed = {
                navController.navigate(ShowsNavigation.NavGraphs.SHOWS_NAV_GRAPH.route)
            },
            onUseFingerprint = onUseFingerprint
        )

        showsNavGraph(navController)
    }
}