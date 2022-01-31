package com.pablojmuratore.testtvmaze.ui.episodes

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
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
    val posterUrl: String? = episode.image?.medium ?: null

    BoxWithConstraints {
        if (maxWidth < 600.dp) {
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 8.dp,
                        vertical = 16.dp
                    )
            ) {
                PosterContent(posterUrl = posterUrl)
                EpisodeInfoContent(episode = episode)
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                PosterContent(posterUrl = posterUrl)
                Divider(modifier = Modifier.width(16.dp), color = Color.Transparent)
                EpisodeInfoContent(
                    modifier = Modifier.weight(1.0f),
                    episode = episode
                )
            }
        }
    }
}

@Composable
fun PosterContent(
    modifier: Modifier = Modifier
        .width(dimensionResource(id = R.dimen.poster_width_portrait))
        .height(dimensionResource(id = R.dimen.poster_height_portrait)),
    posterUrl: String?
) {
    Poster(modifier = modifier, posterUrl = posterUrl)
}

@Composable
fun EpisodeInfoContent(
    modifier: Modifier = Modifier,
    episode: Episode
) {
    val episodeSummary = HtmlCompat.fromHtml(episode.summary ?: stringResource(id = R.string.no_summary_message), HtmlCompat.FROM_HTML_MODE_LEGACY).toString()

    Column(
        modifier = modifier
    ) {
        Text(
            text = episode.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Divider(modifier = Modifier.height(8.dp), color = Color.Transparent)
        Text(text = stringResource(id = R.string.episode_number, episode.number))
        Text(text = stringResource(id = R.string.season_number, episode.season))
        Divider(modifier = Modifier.height(8.dp), color = Color.Transparent)
        Text(text = episodeSummary)
    }
}