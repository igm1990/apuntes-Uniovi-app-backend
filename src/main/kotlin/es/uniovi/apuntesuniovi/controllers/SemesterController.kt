package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.semesters.CreateSemester
import es.uniovi.apuntesuniovi.controllers.commands.semesters.FindAllSemesters
import es.uniovi.apuntesuniovi.models.Semester
import es.uniovi.apuntesuniovi.services.BaseService
import es.uniovi.apuntesuniovi.services.SemesterService
import es.uniovi.apuntesuniovi.services.dtos.entities.SemesterDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Define semesters endpoints
 */
@RestController
@RequestMapping("/semesters")
class SemesterController @Autowired constructor(
    private val semesterService: SemesterService
) : BaseController<Semester, SemesterDto>(semesterService) {

    override fun create(
        baseService: BaseService<Semester, SemesterDto>,
        json: String
    ): SemesterDto {
        return CreateSemester(semesterService, json).execute()
    }

    override fun findAll(
        baseService: BaseService<Semester, SemesterDto>,
        pageable: Pageable
    ): Page<SemesterDto> {
        return FindAllSemesters(semesterService, pageable).execute()
    }
}