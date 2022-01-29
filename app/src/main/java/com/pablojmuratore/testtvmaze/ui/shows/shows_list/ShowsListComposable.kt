package com.pablojmuratore.testtvmaze.ui.shows.shows_list

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.pablojmuratore.testtvmaze.navigation.AppScreens

fun NavGraphBuilder.showsListComposable(
    navController: NavHostController
) {
    composable(
        route = AppScreens.ShowsListScreen.route
    ) {
        val viewModel = hiltViewModel<ShowsListViewModel>()

        ShowsListScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}