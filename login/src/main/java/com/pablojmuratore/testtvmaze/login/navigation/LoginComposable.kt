package com.pablojmuratore.testtvmaze.login.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pablojmuratore.testtvmaze.login.ui.screens.LoginScreen
import com.pablojmuratore.testtvmaze.login.viewmodels.LoginScreenViewModel

fun NavGraphBuilder.loginComposable(
    onConfigurePinNumber: () -> Unit = {},
    onUsePinNumber: () -> Unit = {},
    onUseFingerprint: () -> Unit = {}
) {
    composable(
        route = LoginNavigation.Screens.LoginMainScreen.route
    ) {
        val viewModel = hiltViewModel<LoginScreenViewModel>()

        LoginScreen(
            isPinConfigured = viewModel.isPinConfigured,
            onConfigurePinNumber = onConfigurePinNumber,
            onUsePinNumber = onUsePinNumber,
            onUseFingerprint = onUseFingerprint
        )
    }
}