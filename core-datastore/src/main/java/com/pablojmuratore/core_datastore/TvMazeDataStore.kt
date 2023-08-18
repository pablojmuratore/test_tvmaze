package com.pablojmuratore.core_datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val DATASTORE_NAME = "tv_maze_data_store"
val Context.tvMazeDataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

class TvMazeDataStore(val context: Context) {
}
