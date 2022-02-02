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

    // login

    object LoginMainScreen : AppScreens(route = "login_main_screen")

    object ConfigurePinNumberScreen : AppScreens(route = "configure_pin_number_screen")
    object UsePinNumberScreen : AppScreens(route = "use_pin_number_screen")

    object UseFingerprintScreen : AppScreens(route = "use_fingerprint_screen")
}