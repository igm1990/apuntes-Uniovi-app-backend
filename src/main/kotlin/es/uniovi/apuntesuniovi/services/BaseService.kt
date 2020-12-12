package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.AbstractAssembler
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Abstract service to define
 */
abstract class BaseService<Entity, Dto>(
    private val repository: PagingAndSortingRepository<Entity, Long>,
    private val assembler: AbstractAssembler<Entity, Dto>
) {
    protected val logService = LogService(this.javaClass)

    /**
     * Create a new element
     *
     * @param dto Element to create
     */
    fun create(dto: Dto): List<Dto> {
        logService.info("create(dto: UserDto) - start")
        val value = assembler.dtoToEntity(dto)
        val result = create(repository, value)
        logService.info("create(dto: UserDto) - end")
        return result.map { entity -> assembler.entityToDto(entity) }
    }

    protected abstract fun create(
        repository: PagingAndSortingRepository<Entity, Long>,
        entity: Entity
    ): List<Entity>

    /**
     * Returns all elements
     */
    fun findAll(pageable: Pageable): Page<Dto> {
        logService.info("findAll() - start")
        val result = findAll(repository, pageable)
        logService.info("findAll() - end")
        return result.map { entity -> assembler.entityToDto(entity) }
    }

    protected abstract fun findAll(
        repository: PagingAndSortingRepository<Entity, Long>,
        pageable: Pageable
    ): Page<Entity>
}