package com.pablojmuratore.testtvmaze.shows.mappers

import com.pablojmuratore.testtvmaze.core.IEntityMapper
import com.pablojmuratore.testtvmaze.shows.models.Episode
import com.pablojmuratore.testtvmaze.shows_network.models.EpisodeDTO

class EpisodeDtoMapper(
    private val posterImageDtoMapper: PosterImageDtoMapper
) : IEntityMapper<EpisodeDTO, Episode> {
    override fun mapFromEntity(entity: EpisodeDTO): Episode {
        return Episode(
            id = entity.id,
            name = entity.name,
            season = entity.season,
            number = entity.number,
            image = entity.image?.let { posterImageDtoMapper.mapFromEntity(it) },
            summary = entity.summary
        )
    }

    override fun mapToEntity(domainModel: Episode): EpisodeDTO {
        return EpisodeDTO(
            id = domainModel.id,
            name = domainModel.name,
            season = domainModel.season,
            number = domainModel.number,
            image = domainModel.image?.let { posterImageDtoMapper.mapToEntity(it) },
            summary = domainModel.summary
        )
    }

    override fun mapFromEntitiesList(entities: List<EpisodeDTO>): List<Episode> {
        return entities.map {
            mapFromEntity(it)
        }
    }

    override fun mapToEntitiesList(domainModels: List<Episode>): List<EpisodeDTO> {
        return domainModels.map {
            mapToEntity(it)
        }
    }
}