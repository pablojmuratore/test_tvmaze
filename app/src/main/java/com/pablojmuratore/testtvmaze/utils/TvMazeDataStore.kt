package com.pablojmuratore.testtvmaze.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

class TvMazeDataStore {
    companion object {
        val LOGIN_PIN_KEY = stringPreferencesKey("login_pin_key")

        val Context.tvMazeDataStore: DataStore<Preferences> by preferencesDataStore(name = "tv_maze_data_store")
    }
}