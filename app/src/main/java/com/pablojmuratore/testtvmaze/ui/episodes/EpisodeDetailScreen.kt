package com.pablojmuratore.testtvmaze.ui.episodes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.model.Episode
import com.pablojmuratore.testtvmaze.ui.components.Poster

@Composable
fun EpisodeDetailScreen(
    episode: Episode
) {
//    val annotatedSummary = HtmlCompat.fromHtml(episode.summary ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)

    Column(
        modifier = Modifier
            .padding(
                horizontal = 8.dp,
                vertical = 16.dp
            )
    ) {
        val episodeSummary = HtmlCompat.fromHtml(episode.summary ?: stringResource(id = R.string.no_summary_message), HtmlCompat.FROM_HTML_MODE_LEGACY).toString()

        episode.image?.medium?.let { posterUrl ->
            Poster(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                posterUrl = posterUrl
            )
            Divider(modifier = Modifier.height(16.dp), color = Color.Transparent)
        }
        Text(
            text = episode.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Divider(modifier = Modifier.height(8.dp), color = Color.Transparent)
        Text(text = stringResource(id = R.string.episode_number, episode.number))
        Text(text = stringResource(id = R.string.season_number, episode.season))
        Divider(modifier = Modifier.height(8.dp), color = Color.Transparent)
        Text( text = episodeSummary )
    }
}