package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.dtos.entities.UnitSubjectDto
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.UnitSubjectService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/unitSubjects")
@Api(tags = ["Unit Subjects"])
class UnitSubjectController @Autowired constructor(
    private val unitSubjectService: UnitSubjectService
) {
    private val logService = LogService(this.javaClass)

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TEACHER')")
    @PostMapping(value = ["/create/{id}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Create a unit of a subject")
    fun create(
        @ApiParam(name = "unitSubjectDto", value = "Unit of a subject to create")
        @Valid @RequestBody unitSubjectDto: UnitSubjectDto
    ): ResponseEntity<UnitSubjectDto> {
        logService.info("create(unitSubjectDto: UnitSubjectDto) - start")
        val result = unitSubjectService.create(unitSubjectDto)
        logService.info("create(unitSubjectDto: UnitSubjectDto>) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }
}