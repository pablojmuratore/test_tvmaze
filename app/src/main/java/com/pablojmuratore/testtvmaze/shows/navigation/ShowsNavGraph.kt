package com.pablojmuratore.testtvmaze.shows.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation

fun NavGraphBuilder.showsNavGraph(
    navController: NavHostController
) {
    navigation(
        route = ShowsNavigation.NavGraphs.SHOWS_NAV_GRAPH.route,
        startDestination = ShowsNavigation.Screens.ShowsListScreen.route
    ) {
        showsListComposable(
            onShowClicked = { showInfo ->
                navController.navigate(ShowsNavigation.Screens.ShowDetailScreen.generateRoute(showInfo.show.id))
            }
        )

        showDetailComposable(navController)
    }
}