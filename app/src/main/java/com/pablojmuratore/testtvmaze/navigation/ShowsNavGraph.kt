package com.pablojmuratore.testtvmaze.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.pablojmuratore.testtvmaze.ui.shows.show_detail.showDetailComposable
import com.pablojmuratore.testtvmaze.ui.shows.shows_list.showsListComposable

const val SHOWS_NAV_GRAPH = "shows_nav_graph"

fun NavGraphBuilder.showsNavGraph(
    navController: NavHostController
) {
    navigation(
        route = SHOWS_NAV_GRAPH,
        startDestination = AppScreens.ShowsListScreen.route
    ) {
        showsListComposable(navController)

        showDetailComposable(navController)
    }
}