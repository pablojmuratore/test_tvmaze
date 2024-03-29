package com.pablojmuratore.testtvmaze.core_ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    text: String
) {
    Surface(
        color = MaterialTheme.colorScheme.secondary,
        shape = RoundedCornerShape(size = 8.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = text
        )
    }
}

@Preview
@Composable
fun ChipPreview() {
    Chip("Text 1")
}