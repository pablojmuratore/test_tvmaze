package com.pablojmuratore.testtvmaze.ui.login.use_pin_number

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pablojmuratore.testtvmaze.navigation.AppScreens
import com.pablojmuratore.testtvmaze.ui.login.LoginViewModel

fun NavGraphBuilder.usePinNumberComposable(
    onPinValid: () -> Unit = {}
) {
    composable(
        route = AppScreens.UsePinNumberScreen.route
    ) {
        val viewModel = hiltViewModel<LoginViewModel>()

        UsePinNumberScreen(
            viewModel = viewModel,
            onPinValid = onPinValid
        )
    }
}