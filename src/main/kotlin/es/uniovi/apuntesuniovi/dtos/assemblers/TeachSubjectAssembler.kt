package es.uniovi.apuntesuniovi.dtos.assemblers

import es.uniovi.apuntesuniovi.dtos.entities.TeachSubjectDto
import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.subjects.FindSubjectById
import es.uniovi.apuntesuniovi.services.commands.users.FindUserById
import org.springframework.util.Assert

/**
 * Define the entity and dto conversion methods of teachSubjects
 */
class TeachSubjectAssembler(
    private val subjectRepository: SubjectRepository,
    private val userRepository: UserRepository
) : AbstractAssembler<TeachSubject, TeachSubjectDto>() {
    override fun entityToDto(entity: TeachSubject?): TeachSubjectDto {
        logService.info("entityToDto(entity: TeachSubject) - start")
        entity?.let {
            val result = TeachSubjectDto(
                id = it.id,
                subjectId = it.subject.id!!,
                teacherId = it.teacher.id!!
            )
            logService.info("entityToDto(entity: TeachSubject) - end")
            return result
        }
        throw IllegalArgumentException(TeachSubjectMessages.NULL)
    }

    override fun dtoToEntity(dto: TeachSubjectDto?): TeachSubject {
        logService.info("dtoToEntity(dto: TeachSubjectDto) - start")
        dto?.let {
            val result = TeachSubject()
            result.id = it.id
            result.subject = FindSubjectById(subjectRepository, it.subjectId).execute()
            result.teacher = FindUserById(userRepository, it.teacherId).execute()
            Assert.isTrue(result.teacher.role == RoleType.ROLE_TEACHER, TeachSubjectMessages.INVALID_USER_ROLE)
            logService.info("dtoToEntity(dto: TeachSubjectDto) - end")
            return result
        }
        throw IllegalArgumentException(TeachSubjectMessages.NULL)
    }
}