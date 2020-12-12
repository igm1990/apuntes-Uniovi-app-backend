package es.uniovi.apuntesuniovi.services.commands.careers

import es.uniovi.apuntesuniovi.models.Career
import es.uniovi.apuntesuniovi.repositories.CareerRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindByIdService

/**
 * Return career by id in service layer
 */
class FindCareerByIdService(
    careerRepository: CareerRepository,
    id: Long
) : BaseFindByIdService<Career>(careerRepository, id)