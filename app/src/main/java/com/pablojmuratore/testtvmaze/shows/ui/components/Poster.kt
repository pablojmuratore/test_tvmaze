package com.pablojmuratore.testtvmaze.shows.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import com.pablojmuratore.testtvmaze.R

@Composable
fun Poster(
    modifier: Modifier = Modifier,
    posterUrl: String? = null
) {
    val posterImage = if (!posterUrl.isNullOrBlank()) {
        rememberAsyncImagePainter(
            model = posterUrl
        )
    } else {
        rememberAsyncImagePainter(
            model = R.drawable.ic_baseline_local_movies_24
        )
    }

    Image(
        modifier = modifier,
        painter = posterImage,
        contentDescription = null
    )
}