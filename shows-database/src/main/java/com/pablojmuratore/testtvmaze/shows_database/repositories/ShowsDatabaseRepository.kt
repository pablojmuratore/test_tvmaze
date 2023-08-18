package com.pablojmuratore.testtvmaze.shows_database.repositories

import com.pablojmuratore.testtvmaze.shows.models.ILikedShowsDao
import com.pablojmuratore.testtvmaze.shows.models.LikedShowEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ShowsDatabaseRepository
@Inject constructor(
    private val likedShowsDao: ILikedShowsDao
) : IShowsDatabaseRepository {
    override suspend fun getShowLiked(showId: Long): Flow<Boolean> {
        return likedShowsDao.getById(showId).map {
            it != null
        }
    }

    override fun getLikedShows(): Flow<List<LikedShowEntity>> {
        return likedShowsDao.getAll()
    }

    override suspend fun setShowLiked(showId: Long, liked: Boolean) {
        val likedShow = LikedShowEntity(showId)

        if (liked) {
            likedShowsDao.insert(likedShow)
        } else {
            likedShowsDao.delete(likedShow)
        }
    }
}