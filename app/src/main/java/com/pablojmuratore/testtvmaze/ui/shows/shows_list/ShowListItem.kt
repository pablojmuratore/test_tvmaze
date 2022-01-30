package com.pablojmuratore.testtvmaze.ui.shows.shows_list

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.model.ShowInfo

@Composable
fun ShowListItem(
    showInfo: ShowInfo,
    onShowClicked: (showInfo: ShowInfo) -> Unit = {}
) {
    Surface(
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .height(96.dp)
                .clickable {
                    onShowClicked(showInfo)
                }
                .padding(8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                val posterUrl: String? = showInfo.show.image?.medium ?: null
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

                Log.d("---x", "show: ${showInfo.show.name} - url: $posterUrl")

                Image(
                    modifier = Modifier
                        .size(
                            width = dimensionResource(id = R.dimen.show_list_item_width),
                            height = dimensionResource(id = R.dimen.show_list_item_height)
                        ),
                    painter = posterImage,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier.weight(1.0f),
                    text = showInfo.show.name
                )
            }
        }
    }
}