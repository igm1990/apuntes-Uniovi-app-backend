package es.uniovi.apuntesuniovi.servicies.commands

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.SubjectMessages
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.validators.impl.ValidatorId
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Find entity by id
 */
abstract class AbstractFindById<Entity>(
    private val repository: JpaRepository<Entity, Long>,
    private val id: Long
) : Command<List<Entity>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<Entity> {
        logService.info("execute() - start")
        if (ValidatorId(id).isValid()) {
            val optional = repository.findById(id)
            if (optional.isPresent) {
                logService.info("execute() - end")
                return listOf(optional.get())
            }
            logService.error("execute() - error")
            throw IllegalArgumentException(SubjectMessages.NOT_EXISTS)
        }
        logService.error("execute() - error")
        throw IllegalArgumentException(SubjectMessages.INVALID_ID)
    }
}