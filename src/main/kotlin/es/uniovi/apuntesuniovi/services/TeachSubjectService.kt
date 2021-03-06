package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.dtos.assemblers.SubjectAssembler
import es.uniovi.apuntesuniovi.dtos.assemblers.TeachSubjectAssembler
import es.uniovi.apuntesuniovi.dtos.assemblers.UserAssembler
import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.dtos.entities.TeachSubjectDto
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.teachSubjects.CreateTeachSubject
import es.uniovi.apuntesuniovi.services.commands.teachSubjects.FindSubjectsByTeacherId
import es.uniovi.apuntesuniovi.services.commands.teachSubjects.FindTeachSubjectBySubjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TeachSubjectService @Autowired constructor(
    userRepository: UserRepository,
    subjectRepository: SubjectRepository,
    private val teachSubjectRepository: TeachSubjectRepository
) {
    private val logService = LogService(this.javaClass)
    private val teachSubjectAssembler = TeachSubjectAssembler(
        subjectRepository, userRepository
    )
    private val userAssembler = UserAssembler()
    private val subjectAssembler = SubjectAssembler()

    fun create(subjectId: Long, list: List<TeachSubjectDto>): List<TeachSubjectDto> {
        logService.info("create(list: List<TeachSubjectDto>) - start")
        val teachSubjects = list.map { dto -> teachSubjectAssembler.dtoToEntity(dto) }
        val result = CreateTeachSubject(teachSubjectRepository, subjectId, teachSubjects).execute()
        logService.info("create(list: List<TeachSubjectDto>) - end")
        return result.map { teachSubject -> teachSubjectAssembler.entityToDto(teachSubject) }
    }

    fun findTeachersBySubjectId(id: Long): List<UserDto> {
        logService.info("findTeachersBySubjectId(id: Long) - start")
        val result = FindTeachSubjectBySubjectId(teachSubjectRepository, id).execute()
        logService.info("findTeachersBySubjectId(id: Long) - end")
        return result.map { teachSubject -> convertUser(teachSubject) }
    }

    fun findSubjectsByTeacherId(id: Long): List<SubjectDto> {
        logService.info("findTeachersBySubjectId(id: Long) - start")
        val result = FindSubjectsByTeacherId(teachSubjectRepository, id).execute()
        logService.info("findTeachersBySubjectId(id: Long) - end")
        return result.map { subject -> subjectAssembler.entityToDto(subject) }
    }

    private fun convertUser(teachSubject: TeachSubject): UserDto {
        val user = userAssembler.entityToDto(teachSubject.teacher)
        user.password = null
        return user
    }
}