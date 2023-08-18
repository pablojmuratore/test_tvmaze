package com.pablojmuratore.testtvmaze.shows.mappers

import com.pablojmuratore.testtvmaze.core.IEntityMapper
import com.pablojmuratore.testtvmaze.shows.models.ShowEmbeddedData
import com.pablojmuratore.testtvmaze.shows_network.models.ShowEmbeddedDataDTO

class ShowEmbeddedDataDtoMapper(
    private val episodeDtoMapper: EpisodeDtoMapper
) : IEntityMapper<ShowEmbeddedDataDTO, ShowEmbeddedData> {
    override fun mapFromEntity(entity: ShowEmbeddedDataDTO): ShowEmbeddedData {
        return ShowEmbeddedData(
            episodes = episodeDtoMapper.mapFromEntitiesList(entity.episodes)
        )
    }

    override fun mapToEntity(domainModel: ShowEmbeddedData): ShowEmbeddedDataDTO {
        return ShowEmbeddedDataDTO(
            episodes = episodeDtoMapper.mapToEntitiesList(domainModel.episodes)
        )
    }

    override fun mapFromEntitiesList(entities: List<ShowEmbeddedDataDTO>): List<ShowEmbeddedData> {
        return entities.map {
            mapFromEntity(it)
        }
    }

    override fun mapToEntitiesList(domainModels: List<ShowEmbeddedData>): List<ShowEmbeddedDataDTO> {
        return domainModels.map {
            mapToEntity(it)
        }
    }
}