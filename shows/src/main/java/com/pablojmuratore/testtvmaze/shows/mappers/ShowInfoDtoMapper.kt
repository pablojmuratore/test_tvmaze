package com.pablojmuratore.testtvmaze.shows.mappers

import com.pablojmuratore.testtvmaze.core.IEntityMapper
import com.pablojmuratore.testtvmaze.shows.models.ShowInfo
import com.pablojmuratore.testtvmaze.shows_network.models.ShowInfoDTO

class ShowInfoDtoMapper(
    private val showDtoMapper: ShowDtoMapper
) : IEntityMapper<ShowInfoDTO, ShowInfo> {
    override fun mapFromEntity(entity: ShowInfoDTO): ShowInfo {
        return ShowInfo(
            score = entity.score,
            show = showDtoMapper.mapFromEntity(entity.show)
        )
    }

    override fun mapToEntity(domainModel: ShowInfo): ShowInfoDTO {
        return ShowInfoDTO(
            score = domainModel.score,
            show = showDtoMapper.mapToEntity(domainModel.show)
        )
    }

    override fun mapFromEntitiesList(entities: List<ShowInfoDTO>): List<ShowInfo> {
        return entities.map {
            mapFromEntity(it)
        }
    }

    override fun mapToEntitiesList(domainModels: List<ShowInfo>): List<ShowInfoDTO> {
        return domainModels.map {
            mapToEntity(it)
        }
    }
}