package es.uniovi.apuntesuniovi.services.dtos.assemblers

import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.subjects.FindSubjectByIdService
import es.uniovi.apuntesuniovi.services.commands.users.FindUserByIdService
import es.uniovi.apuntesuniovi.services.dtos.entities.TeachSubjectDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Define the entity and dto conversion methods of teachSubjects
 */
@Service
class TeachSubjectAssembler @Autowired constructor(
    private val subjectRepository: SubjectRepository,
    private val userRepository: UserRepository
) : AbstractAssembler<TeachSubject, TeachSubjectDto>() {
    override fun entityToDto(entity: TeachSubject?): TeachSubjectDto {
        logService.info("entityToDto(entity: TeachSubject) - start")
        entity?.let {
            val result = TeachSubjectDto(
                id = it.id,
                isCoordinator = it.isCoordinator,
                subjectId = it.subject.id!!,
                teacherId = it.teacher.id!!
            )
            logService.info("entityToDto(entity: TeachSubject) - end")
            return result
        }
        logService.error("entityToDto(entity: TeachSubject) - error")
        throw IllegalArgumentException(TeachSubjectMessages.NULL)
    }

    override fun dtoToEntity(dto: TeachSubjectDto?): TeachSubject {
        logService.info("dtoToEntity(dto: TeachSubjectDto) - start")
        dto?.let {
            val result = TeachSubject()
            result.id = it.id
            result.isCoordinator = it.isCoordinator
            result.subject = FindSubjectByIdService(subjectRepository, it.subjectId).execute()[0]
            result.teacher = FindUserByIdService(userRepository, it.teacherId).execute()[0]
            if (result.teacher.role != RoleType.TEACHER) {
                logService.error("dtoToEntity(dto: TeachSubjectDto) - error")
                throw IllegalArgumentException(TeachSubjectMessages.INVALID_USER_ROLE)
            }
            logService.info("dtoToEntity(dto: TeachSubjectDto) - end")
            return result
        }
        logService.error("dtoToEntity(dto: TeachSubjectDto) - error")
        throw IllegalArgumentException(TeachSubjectMessages.NULL)
    }
}