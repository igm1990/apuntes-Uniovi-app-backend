package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.models.Course
import es.uniovi.apuntesuniovi.repositories.CourseRepository
import es.uniovi.apuntesuniovi.services.commands.courses.CreateCourseService
import es.uniovi.apuntesuniovi.services.commands.courses.FindAllCoursesService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.CourseAssembler
import es.uniovi.apuntesuniovi.services.dtos.entities.CourseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service

/**
 * Service to manage courses
 */
@Service
class CourseService @Autowired constructor(
  private val courseRepository: CourseRepository,
  courseAssembler: CourseAssembler
) : BaseService<Course, CourseDto>(courseRepository, courseAssembler) {

  override fun create(
    repository: PagingAndSortingRepository<Course, Long>,
    entity: Course
  ): Course {
    return CreateCourseService(courseRepository, entity).execute()
  }

  override fun findAll(
    repository: PagingAndSortingRepository<Course, Long>,
    pageable: Pageable
  ): Page<Course> {
    return FindAllCoursesService(courseRepository, pageable).execute()
  }
}