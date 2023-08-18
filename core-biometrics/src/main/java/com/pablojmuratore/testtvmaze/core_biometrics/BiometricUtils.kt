package com.pablojmuratore.testtvmaze.core_biometrics

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL

class BiometricUtils {
    companion object {
        fun getBiometricSupport(context: Context): BiometricSupport {
            val biometricManager = BiometricManager.from(context)
            when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
                BiometricManager.BIOMETRIC_SUCCESS -> {
                    return BiometricSupport.CAN_AUTHENTICATE
                }

                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    return BiometricSupport.MUST_ENROLL
                }

                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE,
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE,
                BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                    return BiometricSupport.NO_HARDWARE
                }

                else -> {
                    return BiometricSupport.UNKNOWN
                }
            }
        }
    }

    enum class BiometricSupport {
        CAN_AUTHENTICATE, NO_HARDWARE, MUST_ENROLL, UNKNOWN
    }
}