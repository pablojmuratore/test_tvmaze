package com.pablojmuratore.testtvmaze.ui.shows.show_detail

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pablojmuratore.testtvmaze.navigation.AppScreens

fun NavGraphBuilder.showDetailComposable(
    navController: NavHostController
) {
    composable(
        route = AppScreens.ShowDetailScreen.route,
        arguments = listOf(
            navArgument(AppScreens.PARAM_ID) {
                type = NavType.LongType
            }
        )
    ) {
        val showId = it.arguments?.getLong(AppScreens.PARAM_ID) ?: 0L
        val viewModel = hiltViewModel<ShowDetailViewModel>()

        viewModel.loadShow(showId)

        ShowDetailScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}