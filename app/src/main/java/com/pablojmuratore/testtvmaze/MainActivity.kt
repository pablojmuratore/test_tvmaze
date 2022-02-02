package com.pablojmuratore.testtvmaze

import android.Manifest
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import com.pablojmuratore.testtvmaze.navigation.MainNavHost
import com.pablojmuratore.testtvmaze.ui.theme.TestTvMazeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var cancellationSignal: CancellationSignal? = null

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TvMazeApp(
                onUseFingerPrint = {
                    scanFingerprint()
                }
            )
        }
    }

    private val authenticationCallback: BiometricPrompt.AuthenticationCallback =
        @RequiresApi(Build.VERSION_CODES.P)
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)

                setContent {
                    TvMazeApp(
                        isFingerprintAuthenticated = true
                    )
                }
            }
        }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkFingerprintSupport(): Boolean {
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if (!keyguardManager.isDeviceSecure) {
            return true
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
            return false
        }

        return packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun scanFingerprint() {
        if (checkFingerprintSupport()) {
            val fingerprintPrompt = BiometricPrompt.Builder(this).apply {
                setTitle(getString(R.string.scan_fingerprint_message))
                setNegativeButton(getString(R.string.cancel), mainExecutor) { _, _ -> }
            }.build()

            fingerprintPrompt.authenticate(getCancellationSignal(), mainExecutor, authenticationCallback)
        }
    }

    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal().apply {
            setOnCancelListener { }
        }

        return cancellationSignal as CancellationSignal
    }
}

@Composable
fun TvMazeApp(
    onUseFingerPrint: () -> Unit = {},
    isFingerprintAuthenticated: Boolean = false
) {
    TestTvMazeTheme {
        val navController = rememberNavController()

        MainNavHost(
            navController = navController,
            onUseFingerprint = onUseFingerPrint,
            isFingerprintAuthenticated = isFingerprintAuthenticated
        )
    }
}