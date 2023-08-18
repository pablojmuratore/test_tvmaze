package com.pablojmuratore.testtvmaze.login

import androidx.datastore.preferences.core.stringPreferencesKey

class LoginDataStore {
    companion object {
        val LOGIN_PIN_KEY = stringPreferencesKey("login_pin_key")
    }
}