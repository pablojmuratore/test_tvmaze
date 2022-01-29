package com.pablojmuratore.testtvmaze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.pablojmuratore.testtvmaze.navigation.MainNavHost
import com.pablojmuratore.testtvmaze.ui.theme.TestTvMazeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TvMazeApp()
        }
    }
}

@Composable
fun TvMazeApp() {
    TestTvMazeTheme {
        val navController = rememberNavController()

        MainNavHost(
            navController
        )
    }
}