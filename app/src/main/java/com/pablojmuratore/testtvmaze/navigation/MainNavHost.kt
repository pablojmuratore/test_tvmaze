package com.pablojmuratore.testtvmaze.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MainNavHost(
    navController: NavHostController,
    onUseFingerprint: () -> Unit = {},
    isFingerprintAuthenticated: Boolean = false
) {
    val startDestination = if (isFingerprintAuthenticated) SHOWS_NAV_GRAPH else LOGIN_NAV_GRAPH

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        loginNavGraph(
            navController,
            onLoginPassed = {
                navController.navigate(SHOWS_NAV_GRAPH)
            },
            onUseFingerprint = onUseFingerprint
        )

        showsNavGraph(navController)
    }
}