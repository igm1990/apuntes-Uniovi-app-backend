package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

/**
 * Manage the users table
 */
interface UserRepository : PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
  /**
   * Find user by username
   */
  fun findByUsername(userName: String): Optional<User>

  /**
   * Find user by numberIdentification
   */
  fun findByNumberIdentification(numberIdentification: String): Optional<User>

}