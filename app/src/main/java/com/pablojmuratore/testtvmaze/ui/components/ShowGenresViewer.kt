package com.pablojmuratore.testtvmaze.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ShowGenresViewer(
    genres: List<String> = emptyList()
) {
    val genresText = genres.joinToString()

    Text(text = genresText)
}