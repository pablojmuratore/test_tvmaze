package com.pablojmuratore.testtvmaze.utils

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL

class BiometricUtils {
    companion object {
        fun checkBiometric(context: Context): CheckResponse {
            val biometricManager = BiometricManager.from(context)
            when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
                BiometricManager.BIOMETRIC_SUCCESS -> {
                    return CheckResponse.CAN_AUTHENTICATE
                }
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    return CheckResponse.MUST_ENROLL
                }
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE,
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE,
                BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                    return CheckResponse.NO_HARDWARE
                }
                else -> {
                    return CheckResponse.UNKNOWN
                }
            }
        }
    }

    enum class CheckResponse {
        CAN_AUTHENTICATE, NO_HARDWARE, MUST_ENROLL, UNKNOWN
    }
}