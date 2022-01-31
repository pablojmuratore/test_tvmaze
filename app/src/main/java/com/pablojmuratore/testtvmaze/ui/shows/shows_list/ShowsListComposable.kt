package com.pablojmuratore.testtvmaze.ui.shows.shows_list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.navigation.AppScreens

fun NavGraphBuilder.showsListComposable(
    navController: NavHostController
) {
    composable(
        route = AppScreens.ShowsListScreen.route
    ) {
        val viewModel = hiltViewModel<ShowsListViewModel>()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) }
                )
            }
        ) { innerPadding ->
            ShowsListScreen(
                modifier = Modifier.padding(innerPadding),
                viewModel = viewModel,
                onShowInfoClicked = { showInfo ->
                    navController.navigate(AppScreens.ShowDetailScreen.generateRoute(showInfo.show.id))
                }
            )
        }
    }
}