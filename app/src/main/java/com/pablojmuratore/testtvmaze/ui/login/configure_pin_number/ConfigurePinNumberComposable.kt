package com.pablojmuratore.testtvmaze.ui.login.configure_pin_number

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.pablojmuratore.testtvmaze.navigation.AppScreens
import com.pablojmuratore.testtvmaze.ui.login.LoginViewModel

fun NavGraphBuilder.configurePinNumberComposable(
    onPinNumberConfigured: () -> Unit = {}
) {
    composable(
        route = AppScreens.ConfigurePinNumberScreen.route
    ) {
        val viewModel = hiltViewModel<LoginViewModel>()

        ConfigurePinNumberScreen(
            viewModel = viewModel,
            onPinNumberConfigured = onPinNumberConfigured
        )
    }
}