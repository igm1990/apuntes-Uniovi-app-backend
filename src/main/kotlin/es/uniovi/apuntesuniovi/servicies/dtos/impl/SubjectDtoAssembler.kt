package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.entities.Subject
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.SubjectMessages
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.UserMessages
import es.uniovi.apuntesuniovi.servicies.dtos.AbstractDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import org.springframework.stereotype.Service

@Service
class SubjectDtoAssembler : AbstractDtoAssembler<Subject, SubjectDto>() {
    override fun entityToDto(entity: Subject?): SubjectDto {
        logService.info("entityToDto(entity: ${entity}) - start")
        entity?.let {
            val result = SubjectDto(
                    id = it.id,
                    name = it.name)
            logService.info("entityToDto(entity: ${entity}) - end")
            return result
        }
        logService.error("entityToDto(entity: ${entity}) - error")
        throw IllegalArgumentException(SubjectMessages.NULL)
    }

    override fun dtoToEntity(dto: SubjectDto?): Subject {
        logService.info("dtoToEntity(dto: ${dto}) - start")
        dto?.let {
            val result = Subject()
            result.id = it.id
            result.name = it.name
            logService.info("dtoToEntity(dto: ${dto}) - end")
            return result
        }
        logService.error("dtoToEntity(dto: ${dto}) - error")
        throw IllegalArgumentException(SubjectMessages.NULL)
    }
}