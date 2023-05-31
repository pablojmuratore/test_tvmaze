package com.pablojmuratore.testtvmaze.shows.navigation

class ShowsNavigation {
    enum class NavGraphs(val route: String) {
        SHOWS_NAV_GRAPH("shows_nav_graph")
    }

    sealed class Screens(val route: String) {
        companion object {
            const val PARAM_ID = "id"
        }

        // shows
        object ShowsListScreen : Screens(route = "shows_list_screen")

        object ShowDetailScreen : Screens(route = "show_detail_screen/{id}") {
            fun generateRoute(showId: Long): String {
                return this.route.replace("{$PARAM_ID}", showId.toString())
            }
        }
    }
}