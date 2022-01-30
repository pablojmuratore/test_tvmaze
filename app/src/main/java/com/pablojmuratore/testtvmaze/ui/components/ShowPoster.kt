package com.pablojmuratore.testtvmaze.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import coil.compose.rememberImagePainter
import com.pablojmuratore.testtvmaze.R

@Composable
fun ShowPoster(
    posterUrl: String? = null,
    width: Dp = dimensionResource(id = R.dimen.poster_width),
    height: Dp = dimensionResource(id = R.dimen.poster_height)
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
        modifier = Modifier
            .size(
                width = width,
                height = height
            ),
        painter = posterImage,
        contentDescription = null
    )
}