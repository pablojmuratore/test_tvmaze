package com.pablojmuratore.testtvmaze.shows.mappers

import com.pablojmuratore.testtvmaze.core.IEntityMapper
import com.pablojmuratore.testtvmaze.shows.models.PosterImage
import com.pablojmuratore.testtvmaze.shows_network.models.PosterImageDTO

class PosterImageDtoMapper : IEntityMapper<PosterImageDTO, PosterImage> {
    override fun mapFromEntity(entity: PosterImageDTO): PosterImage {
        return PosterImage(
            medium = entity.medium
        )
    }

    override fun mapToEntity(domainModel: PosterImage): PosterImageDTO {
        return PosterImageDTO(
            medium = domainModel.medium
        )
    }

    override fun mapFromEntitiesList(entities: List<PosterImageDTO>): List<PosterImage> {
        return entities.map {
            mapFromEntity(it)
        }
    }

    override fun mapToEntitiesList(domainModels: List<PosterImage>): List<PosterImageDTO> {
        return domainModels.map {
            mapToEntity(it)
        }
    }
}