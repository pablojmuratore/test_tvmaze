package com.pablojmuratore.testtvmaze.ui.shows.show_detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pablojmuratore.testtvmaze.R
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

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            ShowDetailScreen(
                modifier = Modifier.padding(innerPadding),
                viewModel = viewModel
            )
        }
    }
}