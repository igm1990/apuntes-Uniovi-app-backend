package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.entities.Subject
import es.uniovi.apuntesuniovi.servicies.dtos.AbstractDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import org.springframework.stereotype.Service

@Service
class SubjectDtoAssembler : AbstractDtoAssembler<Subject, SubjectDto>() {
    override fun entityToDto(entity: Subject): SubjectDto {
        logService.info("entityToDto(entity: ${entity}) - start")
        val result = SubjectDto(
                id = entity.id,
                name = entity.name)
        logService.info("entityToDto(entity: ${entity}) - end")
        return result
    }

    override fun dtoToEntity(dto: SubjectDto): Subject {
        logService.info("dtoToEntity(dto: ${dto}) - start")
        val result = Subject()
        result.id = dto.id
        result.name = dto.name
        logService.info("dtoToEntity(dto: ${dto}) - end")
        return result
    }
}