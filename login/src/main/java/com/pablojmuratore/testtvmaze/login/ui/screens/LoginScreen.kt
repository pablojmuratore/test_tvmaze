package com.pablojmuratore.testtvmaze.login.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablojmuratore.testtvmaze.core_biometrics.BiometricUtils
import com.pablojmuratore.testtvmaze.login.R

@Composable
fun LoginScreen(
    isPinConfigured: Boolean = false,
    onConfigurePinNumber: () -> Unit = {},
    onUsePinNumber: () -> Unit = {},
    onUseFingerprint: () -> Unit = {}
) {
    val context = LocalContext.current
    val canUseFingerprint = BiometricUtils.getBiometricSupport(context)

    LoginScreenUI(
        isPinConfigured = isPinConfigured,
        canUseFingerprint = canUseFingerprint,
        onPinClicked = {
            if (!isPinConfigured) {
                onConfigurePinNumber()
            } else {
                onUsePinNumber()
            }
        },
        onFingerprintClicked = {
            if (canUseFingerprint == BiometricUtils.BiometricSupport.CAN_AUTHENTICATE) {
                onUseFingerprint()
            }
        }
    )
}

@Composable
fun LoginScreenUI(
    isPinConfigured: Boolean = false,
    canUseFingerprint: BiometricUtils.BiometricSupport = BiometricUtils.BiometricSupport.UNKNOWN,
    onPinClicked: () -> Unit = {},
    onFingerprintClicked: () -> Unit = {},
) {
    val pinButtonText = if (!isPinConfigured) {
        stringResource(id = R.string.configure_pin_number)
    } else {
        stringResource(id = R.string.use_pin_number)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = stringResource(id = R.string.login))

        Divider(modifier = Modifier.height(16.dp), color = Color.Transparent)

        Button(onClick = onPinClicked) {
            Text(text = pinButtonText)
        }

        if (canUseFingerprint == BiometricUtils.BiometricSupport.CAN_AUTHENTICATE || canUseFingerprint == BiometricUtils.BiometricSupport.MUST_ENROLL) {
            Divider(modifier = Modifier.height(8.dp), color = Color.Transparent)

            if (canUseFingerprint == BiometricUtils.BiometricSupport.CAN_AUTHENTICATE) {
                Button(onClick = onFingerprintClicked) {
                    Text(text = stringResource(id = R.string.use_fingerprint))
                }
            }

            if (canUseFingerprint == BiometricUtils.BiometricSupport.MUST_ENROLL) {
                Text(text = stringResource(id = R.string.must_enroll_fingerprint_message))
            }
        }
    }
}


// previews

@Preview(showBackground = true)
@Composable
fun PreviewLoginMainScreenUI() {
    LoginScreenUI()
}