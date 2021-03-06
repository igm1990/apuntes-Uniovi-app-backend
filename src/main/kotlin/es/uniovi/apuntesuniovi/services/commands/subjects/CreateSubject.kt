package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseCreate

/**
 * Create a subject in service layer
 */
class CreateSubject(
    subjectRepository: SubjectRepository,
    subject: Subject
) : BaseCreate<Subject>(subjectRepository, subject) {

    override fun checkData() {
        logService.info("checkData() - start")
        logService.info("checkData() - end")
    }
}