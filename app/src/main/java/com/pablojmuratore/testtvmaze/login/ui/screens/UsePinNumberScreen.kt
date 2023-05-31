package com.pablojmuratore.testtvmaze.login.ui.screens

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.R
import com.pablojmuratore.testtvmaze.login.data_states.PinCheckState
import com.pablojmuratore.testtvmaze.login.viewmodels.LoginScreenViewModel
import kotlin.math.absoluteValue

@Composable
fun UsePinNumberScreen(
    viewModel: LoginScreenViewModel,
    onPinValid: () -> Unit = {}
) {
    if ((viewModel.pinCheckState as? PinCheckState.Checked)?.isCorrect == true) {
        onPinValid()
    } else {
        val isPinIncorrect = (viewModel.pinCheckState as? PinCheckState.Checked)?.isCorrect == false

        UsePinNumberScreenUI(
            pin = viewModel.pin,
            onPinChanged = viewModel::onPinChanged,
            pinCheckState = viewModel.pinCheckState,
            isPinIncorrect = isPinIncorrect,
            checkPin = viewModel::checkPin
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview(showBackground = true)
fun UsePinNumberScreenUI(
    pin: String = "",
    onPinChanged: (pin: String) -> Unit = {},
    pinCheckState: PinCheckState = PinCheckState.Unchecked,
    isPinIncorrect: Boolean = false,
    checkPin: (pin: String) -> Unit = {}
) {
    val focusRequester = FocusRequester()
    val isPinValid = pin.length == 4
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            modifier = Modifier
                .focusRequester(focusRequester = focusRequester)
                .focusable(true),
            value = pin,
            onValueChange = {
                try {
                    val newValue = it.toInt().absoluteValue.toString()
                    if (it.length <= 4 && newValue == it) {
                        onPinChanged(it)
                    }
                } catch (e: Exception) {
                }
            },
            label = {
                Text(
                    color = MaterialTheme.colorScheme.primary,
                    text = stringResource(id = R.string.enter_pin)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            trailingIcon = {
                if (pin.isNotBlank()) {
                    IconButton(
                        onClick = {
                            onPinChanged("")
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
        Divider(modifier = Modifier.height(8.dp), color = Color.Transparent)
        Button(
            enabled = isPinValid,
            onClick = {
                keyboardController?.hide()
                checkPin(pin)
            }
        ) {
            Text(text = stringResource(id = R.string.validate))
        }

        if (isPinIncorrect) {
            Divider(modifier = Modifier.height(16.dp), color = Color.Transparent)
            Text(
                text = stringResource(id = R.string.entered_pin_is_invalid),
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
