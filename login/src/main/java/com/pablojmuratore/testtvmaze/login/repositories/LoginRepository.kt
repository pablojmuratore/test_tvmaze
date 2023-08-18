package com.pablojmuratore.testtvmaze.login.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.pablojmuratore.core_datastore.tvMazeDataStore
import com.pablojmuratore.testtvmaze.login.LoginDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginRepository @Inject constructor(): ILoginRepository {
    override fun getLoginPin(context: Context): Flow<String> {
        return context.tvMazeDataStore.data.map { preferences ->
            preferences[LoginDataStore.LOGIN_PIN_KEY] ?: ""
        }
    }

    override suspend fun setLoginPin(pin: String, context: Context) {
        context.tvMazeDataStore.edit { settings ->
            settings[LoginDataStore.LOGIN_PIN_KEY] = pin
        }
    }
}