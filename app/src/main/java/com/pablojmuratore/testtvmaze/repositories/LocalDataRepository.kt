package com.pablojmuratore.testtvmaze.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.pablojmuratore.testtvmaze.database.AppDatabase
import com.pablojmuratore.testtvmaze.model.LikedShow
import com.pablojmuratore.testtvmaze.utils.TvMazeDataStore
import com.pablojmuratore.testtvmaze.utils.TvMazeDataStore.Companion.tvMazeDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataRepository
@Inject constructor(
    private val database: AppDatabase
) : ILocalDataRepository {

    // region shows
    override suspend fun getShowLiked(showId: Long): Flow<Boolean> {
        return database.likedShowsDao().getById(showId).map {
            it != null
        }
    }

    override suspend fun setShowLiked(showId: Long, liked: Boolean) {
        val likedShow = LikedShow(showId)

        if (liked) {
            database.likedShowsDao().insert(likedShow)
        } else {
            database.likedShowsDao().delete(likedShow)
        }
    }

    // endregion

    override fun getLikedShows(): Flow<List<LikedShow>> {
        return database.likedShowsDao().getAll()
    }

    // region login
    override fun getLoginPin(context: Context): Flow<String> {
        return context.tvMazeDataStore.data.map { preferences ->
            preferences[TvMazeDataStore.LOGIN_PIN_KEY] ?: ""
        }
    }

    override suspend fun setLoginPin(pin: String, context: Context) {
        context.tvMazeDataStore.edit { settings ->
            settings[TvMazeDataStore.LOGIN_PIN_KEY] = pin
        }
    }

    // endregion
}