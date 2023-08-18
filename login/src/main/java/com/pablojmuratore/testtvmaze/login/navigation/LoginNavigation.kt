package com.pablojmuratore.testtvmaze.login.navigation

class LoginNavigation {
    enum class NavGraphs(val route: String) {
        LOGIN_NAV_GRAPH("login_nav_graph")
    }

    sealed class Screens(val route: String) {
        companion object {
            const val PARAM_ID = "id"
        }

        object LoginMainScreen : Screens(route = "login_main_screen")

        object ConfigurePinNumberScreen : Screens(route = "configure_pin_number_screen")
        object UsePinNumberScreen : Screens(route = "use_pin_number_screen")

        object UseFingerprintScreen : Screens(route = "use_fingerprint_screen")
    }
}