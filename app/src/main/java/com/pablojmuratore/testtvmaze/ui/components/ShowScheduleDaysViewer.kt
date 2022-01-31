package com.pablojmuratore.testtvmaze.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.R

@Composable
fun ShowScheduleDaysViewer(
    days: List<String> = emptyList()
) {
    // Ideally this should be retrieved from backend and detect first day of week from configuration
    val daysList = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    Row {
        daysList.forEach { day ->
            val textColor = if (day in days) colorResource(id = R.color.schedule_day_text_on) else colorResource(id = R.color.schedule_day_text_off)
            val backgroundColor = if (day in days) colorResource(id = R.color.schedule_day_background_on) else colorResource(id = R.color.schedule_day_background_off)

            Box(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 2.dp)
//                    .size(dimensionResource(id = R.dimen.schedule_day_size))
                    .size(dimensionResource(id = R.dimen.schedule_day_size))
                    .background(color = backgroundColor, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    color = textColor,
                    text = day[0].toString()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowScheduleDaysViewerPreview() {
    ShowScheduleDaysViewer(
        days = listOf("Wednesday")
    )
}