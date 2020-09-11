package es.uniovi.apuntesuniovi.repositories.impl

import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Implement the repository factory interface
 */
@Service
class RepositoryFactoryImpl @Autowired constructor(
        private val personRepository: UserRepository,
        private val subjectRepository: SubjectRepository
) : RepositoryFactory {
    /**
     * Devuelve el repositorio de los usuarios
     */
    override fun getUsers(): UserRepository {
        return personRepository
    }

    /**
     * Devuelve el repositorio de las asignaturas
     */
    override fun getSubjects(): SubjectRepository {
        return subjectRepository
    }
}