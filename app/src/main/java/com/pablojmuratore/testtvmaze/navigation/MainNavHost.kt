package com.pablojmuratore.testtvmaze.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pablojmuratore.testtvmaze.ui.shows.shows_list.showsListComposable

@Composable
fun MainNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.ShowsListScreen.route
    ) {
        showsListComposable(navController)
    }
}