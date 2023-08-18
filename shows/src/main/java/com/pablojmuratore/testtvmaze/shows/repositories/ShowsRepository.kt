package com.pablojmuratore.testtvmaze.shows.repositories

import com.pablojmuratore.testtvmaze.shows.mappers.LikedShowEntityMapper
import com.pablojmuratore.testtvmaze.shows.mappers.ShowDtoMapper
import com.pablojmuratore.testtvmaze.shows.mappers.ShowInfoDtoMapper
import com.pablojmuratore.testtvmaze.shows.models.LikedShow
import com.pablojmuratore.testtvmaze.shows.models.Show
import com.pablojmuratore.testtvmaze.shows.models.ShowInfo
import com.pablojmuratore.testtvmaze.shows_database.repositories.IShowsDatabaseRepository
import com.pablojmuratore.testtvmaze.shows_network.repositories.IShowsNetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ShowsRepository
@Inject constructor(
    private val showsDatabaseRepository: IShowsDatabaseRepository,
    private val showsNetworkRepository: IShowsNetworkRepository,
    private val likedShowEntityMapper: LikedShowEntityMapper,
    private val showDtoMapper: ShowDtoMapper,
    private val showInfoDtoMapper: ShowInfoDtoMapper
) : IShowsRepository {
    override suspend fun searchShows(query: String): List<ShowInfo> {
        return showInfoDtoMapper.mapFromEntitiesList(showsNetworkRepository.searchShows(query))
    }

    override suspend fun loadShowDetail(showId: Long): Show {
        return showDtoMapper.mapFromEntity(showsNetworkRepository.loadShowDetail(showId))
    }

    override suspend fun getShowLiked(showId: Long): Flow<Boolean> {
        return showsDatabaseRepository.getShowLiked(showId)
    }

    override fun getLikedShows(): Flow<List<LikedShow>> {
        return showsDatabaseRepository.getLikedShows().map {
            likedShowEntityMapper.mapFromEntitiesList(it)
        }
    }

    override suspend fun setShowLiked(showId: Long, liked: Boolean) {
        showsDatabaseRepository.setShowLiked(showId, liked)
    }
}