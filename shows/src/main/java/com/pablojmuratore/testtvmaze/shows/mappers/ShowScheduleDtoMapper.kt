package com.pablojmuratore.testtvmaze.shows.mappers

import com.pablojmuratore.testtvmaze.core.IEntityMapper
import com.pablojmuratore.testtvmaze.shows.models.ShowSchedule
import com.pablojmuratore.testtvmaze.shows_network.models.ShowScheduleDTO

class ShowScheduleDtoMapper : IEntityMapper<ShowScheduleDTO, ShowSchedule> {
    override fun mapFromEntity(entity: ShowScheduleDTO): ShowSchedule {
        return ShowSchedule(
            time = entity.time,
            days = entity.days
        )
    }

    override fun mapToEntity(domainModel: ShowSchedule): ShowScheduleDTO {
        return ShowScheduleDTO(
            time = domainModel.time,
            days = domainModel.days
        )
    }

    override fun mapFromEntitiesList(entities: List<ShowScheduleDTO>): List<ShowSchedule> {
        return entities.map {
            mapFromEntity(it)
        }
    }

    override fun mapToEntitiesList(domainModels: List<ShowSchedule>): List<ShowScheduleDTO> {
        return domainModels.map {
            mapToEntity(it)
        }
    }
}