package com.pablojmuratore.testtvmaze.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation

fun NavGraphBuilder.loginNavGraph(
    navController: NavHostController,
    onLoginPassed: () -> Unit = {},
    onUseFingerprint: () -> Unit = {}
) {
    navigation(
        route = LoginNavigation.NavGraphs.LOGIN_NAV_GRAPH.route,
        startDestination = LoginNavigation.Screens.LoginMainScreen.route
    ) {
        loginComposable(
            onConfigurePinNumber = {
                navController.navigate(LoginNavigation.Screens.ConfigurePinNumberScreen.route)
            },
            onUsePinNumber = {
                navController.navigate(LoginNavigation.Screens.UsePinNumberScreen.route)
            },
            onUseFingerprint = {
                onUseFingerprint()
            }
        )

        configurePinNumberComposable(
            onPinNumberConfigured = onLoginPassed
        )

        usePinNumberComposable(
            onPinValid = onLoginPassed
        )
    }
}