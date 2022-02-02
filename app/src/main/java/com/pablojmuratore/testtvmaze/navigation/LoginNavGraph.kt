package com.pablojmuratore.testtvmaze.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.pablojmuratore.testtvmaze.ui.login.configure_pin_number.configurePinNumberComposable
import com.pablojmuratore.testtvmaze.ui.login.loginComposable
import com.pablojmuratore.testtvmaze.ui.login.use_pin_number.usePinNumberComposable

const val LOGIN_NAV_GRAPH = "login_nav_graph"

fun NavGraphBuilder.loginNavGraph(
    navController: NavHostController,
    onLoginPassed: () -> Unit = {},
    onUseFingerprint: () -> Unit = {}
) {
    navigation(
        route = LOGIN_NAV_GRAPH,
        startDestination = AppScreens.LoginMainScreen.route
    ) {
        loginComposable(
            onConfigurePinNumber = {
                navController.navigate(AppScreens.ConfigurePinNumberScreen.route)
            },
            onUsePinNumber = {
                navController.navigate(AppScreens.UsePinNumberScreen.route)
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