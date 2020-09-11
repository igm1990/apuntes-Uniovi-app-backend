package es.uniovi.apuntesuniovi.servicies.dtos

import es.uniovi.apuntesuniovi.log.LogService
import java.util.*
import java.util.function.Consumer

/**
 * Abstract class to define the entity and dto conversion methods
 */
abstract class AbstractDtoAssembler<Entity, Dto> {
    protected val logService = LogService(this.javaClass)

    /**
     * Convert an entity into to dto
     *
     * @param entity Entity to convert
     */
    abstract fun entityToDto(entity: Entity): Dto

    /**
     * Convert an dto into to entity
     *
     * @param dto Dto to convert
     */
    abstract fun dtoToEntity(dto: Dto): Entity

    /**
     * Converts a list of entities to a list of dtos
     *
     * @param entityList List of entities to convert
     */
    fun listToDto(entityList: List<Entity>): List<Dto> {
        logService.info("listToDto(entityList: ${entityList}) - start")
        val dtoList: MutableList<Dto> = ArrayList()
        entityList.forEach(Consumer { entity -> dtoList.add(entityToDto(entity)) })
        logService.info("listToDto(entityList: ${entityList}) - end")
        return dtoList
    }

    /**
     * Converts a list of dtos to a list of entities
     *
     * @param dtoList List of dtos to convert
     */
    fun listToEntities(dtoList: List<Dto>): List<Entity> {
        logService.info("listToEntities(dtoList: ${dtoList}) - start")
        val entityList: MutableList<Entity> = ArrayList()
        dtoList.forEach(Consumer { dto -> entityList.add(dtoToEntity(dto)) })
        logService.info("listToEntities(dtoList: ${dtoList}) - end")
        return entityList
    }
}