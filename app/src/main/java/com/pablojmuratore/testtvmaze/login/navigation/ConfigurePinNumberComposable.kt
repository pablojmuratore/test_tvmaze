package com.pablojmuratore.testtvmaze.login.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pablojmuratore.testtvmaze.login.ui.screens.ConfigurePinNumberScreen
import com.pablojmuratore.testtvmaze.login.viewmodels.LoginScreenViewModel

fun NavGraphBuilder.configurePinNumberComposable(
    onPinNumberConfigured: () -> Unit = {}
) {
    composable(
        route = LoginNavigation.Screens.ConfigurePinNumberScreen.route
    ) {
        val viewModel = hiltViewModel<LoginScreenViewModel>()

        ConfigurePinNumberScreen(
            viewModel = viewModel,
            onPinNumberConfigured = onPinNumberConfigured
        )
    }
}