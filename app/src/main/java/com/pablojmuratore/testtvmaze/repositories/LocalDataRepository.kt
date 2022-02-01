package com.pablojmuratore.testtvmaze.repositories

import com.pablojmuratore.testtvmaze.database.AppDatabase
import com.pablojmuratore.testtvmaze.model.LikedShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataRepository(
    private val database: AppDatabase
) : ILocalDataRepository {
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

    override fun getLikedShows(): Flow<List<LikedShow>> {
        return database.likedShowsDao().getAll()
    }
}