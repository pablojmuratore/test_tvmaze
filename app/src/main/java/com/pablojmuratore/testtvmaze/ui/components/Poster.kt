package com.pablojmuratore.testtvmaze.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import com.pablojmuratore.testtvmaze.R

@Composable
fun Poster(
    modifier: Modifier = Modifier,
    posterUrl: String? = null
) {
    val posterImage = if (!posterUrl.isNullOrBlank()) {
        rememberImagePainter(
            data = posterUrl,
            builder = {
                placeholder(R.drawable.ic_baseline_local_movies_24)
                error(R.drawable.ic_baseline_local_movies_24)
            }
        )
    } else {
        rememberImagePainter(
            data = R.drawable.ic_baseline_local_movies_24
        )
    }

    Image(
        modifier = modifier,
        painter = posterImage,
        contentDescription = null
    )
}