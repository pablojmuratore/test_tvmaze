package com.pablojmuratore.testtvmaze.shows.mappers

import com.pablojmuratore.testtvmaze.core.IEntityMapper
import com.pablojmuratore.testtvmaze.shows.models.LikedShow
import com.pablojmuratore.testtvmaze.shows.models.LikedShowEntity

class ShowEntityMapper : IEntityMapper<LikedShowEntity, LikedShow> {
    override fun mapFromEntity(entity: LikedShowEntity): LikedShow {
        return LikedShow(entity.showId)
    }

    override fun mapToEntity(domainModel: LikedShow): LikedShowEntity {
        return LikedShowEntity(domainModel.showId)
    }

    override fun mapFromEntitiesList(entities: List<LikedShowEntity>): List<LikedShow> {
        return entities.map {
            mapFromEntity(it)
        }
    }

    override fun mapToEntitiesList(domainModels: List<LikedShow>): List<LikedShowEntity> {
        return domainModels.map {
            mapToEntity(it)
        }
    }
}