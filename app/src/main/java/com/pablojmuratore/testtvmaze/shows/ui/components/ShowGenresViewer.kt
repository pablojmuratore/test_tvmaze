package com.pablojmuratore.testtvmaze.shows.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.core_ui.components.Chip

@Composable
fun ShowGenresViewer(
    modifier: Modifier = Modifier,
    genres: List<String> = emptyList()
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        genres.forEach { genre ->
            Chip(genre)
        }
    }
}

@Preview
@Composable
fun ShowGenresViewerPreview() {
    val mockGenres = listOf("genre 1", "genre 2", "genre 3")

    ShowGenresViewer(genres = mockGenres)
}