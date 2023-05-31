package com.pablojmuratore.testtvmaze.login.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pablojmuratore.testtvmaze.login.ui.screens.UsePinNumberScreen
import com.pablojmuratore.testtvmaze.login.viewmodels.LoginScreenViewModel

fun NavGraphBuilder.usePinNumberComposable(
    onPinValid: () -> Unit = {}
) {
    composable(
        route = LoginNavigation.Screens.UsePinNumberScreen.route
    ) {
        val viewModel = hiltViewModel<LoginScreenViewModel>()

        UsePinNumberScreen(
            viewModel = viewModel,
            onPinValid = onPinValid
        )
    }
}