package com.pablojmuratore.testtvmaze.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.model.Episode

@Composable
fun EpisodeListItem(
    episode: Episode
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "${episode.number}")
        Divider(modifier = Modifier.width(8.dp))
        Text(text = episode.name)
    }
}