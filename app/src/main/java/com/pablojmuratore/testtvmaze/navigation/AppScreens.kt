package com.pablojmuratore.testtvmaze.navigation

sealed class AppScreens(val route: String) {
    companion object {
        const val PARAM_ID = "id"
    }

    object ShowsListScreen : AppScreens(route = "shows_list_screen")

    object ShowDetailScreen : AppScreens(route = "show_detail_screen/{id}") {
        fun generateRoute(showId: Long): String {
            return this.route.replace("{$PARAM_ID}", showId.toString())
        }
    }
}