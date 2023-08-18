package com.pablojmuratore.testtvmaze.core

interface IEntityMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel
    fun mapToEntity(domainModel: DomainModel): Entity
    fun mapFromEntitiesList(entities: List<Entity>): List<DomainModel>
    fun mapToEntitiesList(domainModels: List<DomainModel>): List<Entity>
}