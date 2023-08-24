package com.pablojmuratore.testtvmaze.shows.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.pablojmuratore.testtvmaze.shows.R

@Composable
fun PosterContent(
    modifier: Modifier = Modifier
        .width(dimensionResource(id = R.dimen.poster_width_portrait))
        .height(dimensionResource(id = R.dimen.poster_height_portrait)),
    posterUrl: String?
) {
    Poster(modifier = modifier, posterUrl = posterUrl)
}