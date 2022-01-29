package com.pablojmuratore.testtvmaze.ui.components.search_bar

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.R

@Composable
fun SearchBar(
    value: String = "",
    onQueryChanged: (query: String) -> Unit = {},
    onSearchClicked: (value: String) -> Unit = {}
) {
    val focusRequester = FocusRequester()

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier
                .focusRequester(focusRequester = focusRequester)
                .weight(1.0f),
            value = value,
            onValueChange = onQueryChanged,
            label = {
                Text(text = stringResource(id = R.string.enter_search_query))
            },
            singleLine = true,
            trailingIcon = {
                if (value.isNotBlank()) {
                    IconButton(
                        onClick = {
                            onQueryChanged("")
                            focusRequester.requestFocus()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = stringResource(id = R.string.clear_icon)
                        )
                    }
                }
            }
        )

        Button(
            modifier = Modifier.padding(horizontal = 8.dp),
            onClick = {
                Log.d("---x", "click: $value")
                onSearchClicked(value)
            }
        ) {
            Text(text = stringResource(id = R.string.search))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar()
}