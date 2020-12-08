package es.uniovi.apuntesuniovi.services.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.GenericMessages
import es.uniovi.apuntesuniovi.repositories.PageableRepository
import es.uniovi.apuntesuniovi.validators.impl.ValidatorId

/**
 * Find entity by id
 */
abstract class BaseFindByIdService<Entity>(
    private val repository: PageableRepository<Entity>,
    private val id: Long
) : AbstractCommand<List<Entity>>() {
    override fun execute(): List<Entity> {
        logService.info("execute() - start")
        if (ValidatorId(id).isValid()) {
            val optional = repository.findById(id)
            if (optional.isPresent) {
                logService.info("execute() - end")
                return listOf(optional.get())
            }
            logService.error("execute() - error")
            throw IllegalArgumentException(GenericMessages.NOT_EXISTS)
        }
        logService.error("execute() - error")
        throw IllegalArgumentException(GenericMessages.INVALID_ID)
    }
}