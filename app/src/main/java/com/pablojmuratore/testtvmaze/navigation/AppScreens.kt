package com.pablojmuratore.testtvmaze.navigation

sealed class AppScreens(val route: String) {
    companion object {
        const val PARAM_ID = "id"
    }

    object ShowsListScreen : AppScreens(route = "shows_list_screen")
}