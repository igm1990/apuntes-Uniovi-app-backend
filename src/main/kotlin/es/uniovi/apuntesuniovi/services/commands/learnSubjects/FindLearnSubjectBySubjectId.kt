package es.uniovi.apuntesuniovi.services.commands.learnSubjects

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.LearnSubjectMessages
import es.uniovi.apuntesuniovi.models.LearnSubject
import es.uniovi.apuntesuniovi.repositories.LearnSubjectRepository
import es.uniovi.apuntesuniovi.validators.impl.ValidatorId
import org.springframework.util.Assert

/**
 * Return LearnSubject by id in service layer
 */
class FindLearnSubjectBySubjectId(
    private val learnSubjectRepository: LearnSubjectRepository,
    private val id: Long
) : AbstractCommand<List<LearnSubject>>() {

    override fun execute(): List<LearnSubject> {
        logService.info("execute(): List<LearnSubject> - start")
        Assert.isTrue(ValidatorId(id).isValid(), LearnSubjectMessages.INVALID_SUBJECT_ID)
        val list = learnSubjectRepository.findBySubjectId(id)
        logService.info("execute(): List<LearnSubject> - end")
        return list
    }
}