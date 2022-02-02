package com.pablojmuratore.testtvmaze.repositories

import android.content.Context
import com.pablojmuratore.testtvmaze.model.LikedShow
import kotlinx.coroutines.flow.Flow

interface ILocalDataRepository {
    suspend fun getShowLiked(showId: Long): Flow<Boolean>
    suspend fun setShowLiked(showId: Long, liked: Boolean)
    fun getLikedShows(): Flow<List<LikedShow>>

    fun getLoginPin(context: Context): Flow<String>
    suspend fun setLoginPin(pin: String, context: Context)
}