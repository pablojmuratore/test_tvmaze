package com.pablojmuratore.testtvmaze.ui.login

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pablojmuratore.testtvmaze.navigation.AppScreens

fun NavGraphBuilder.loginComposable(
    onConfigurePinNumber: () -> Unit = {},
    onUsePinNumber: () -> Unit = {},
    onUseFingerprint: () -> Unit = {}
) {
    composable(
        route = AppScreens.LoginMainScreen.route
    ) {
        val viewModel = hiltViewModel<LoginViewModel>()

        LoginScreen(
            viewModel = viewModel,
            onConfigurePinNumber = onConfigurePinNumber,
            onUsePinNumber = onUsePinNumber,
            onUseFingerprint = onUseFingerprint
        )
    }
}