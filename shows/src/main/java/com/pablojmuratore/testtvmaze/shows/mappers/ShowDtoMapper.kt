package com.pablojmuratore.testtvmaze.shows.mappers

import com.pablojmuratore.testtvmaze.core.IEntityMapper
import com.pablojmuratore.testtvmaze.shows.models.Show
import com.pablojmuratore.testtvmaze.shows_network.models.ShowDTO

class ShowDtoMapper(
    private val posterImageDtoMapper: PosterImageDtoMapper,
    private val showScheduleDtoMapper: ShowScheduleDtoMapper,
    private val showEmbeddedDataDtoMapper: ShowEmbeddedDataDtoMapper
) : IEntityMapper<ShowDTO, Show> {
    override fun mapFromEntity(entity: ShowDTO): Show {
        return Show(
            id = entity.id,
            name = entity.name,
            image = entity.image?.let { posterImageDtoMapper.mapFromEntity(it) },
            schedule = showScheduleDtoMapper.mapFromEntity(entity.schedule),
            genres = entity.genres,
            summary = entity.summary,
            embeddedData = entity.embeddedData?.let { showEmbeddedDataDtoMapper.mapFromEntity(it) }
        )
    }

    override fun mapToEntity(domainModel: Show): ShowDTO {
        return ShowDTO(
            id = domainModel.id,
            name = domainModel.name,
            image = domainModel.image?.let { posterImageDtoMapper.mapToEntity(it) },
            schedule = showScheduleDtoMapper.mapToEntity(domainModel.schedule),
            genres = domainModel.genres,
            summary = domainModel.summary,
            embeddedData = domainModel.embeddedData?.let { showEmbeddedDataDtoMapper.mapToEntity(it) }
        )
    }

    override fun mapFromEntitiesList(entities: List<ShowDTO>): List<Show> {
        return entities.map {
            mapFromEntity(it)
        }
    }

    override fun mapToEntitiesList(domainModels: List<Show>): List<ShowDTO> {
        return domainModels.map {
            mapToEntity(it)
        }
    }
}