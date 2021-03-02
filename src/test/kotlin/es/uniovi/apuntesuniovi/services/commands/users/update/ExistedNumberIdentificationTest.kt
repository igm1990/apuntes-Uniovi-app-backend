package es.uniovi.apuntesuniovi.services.commands.users.update

import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.users.UpdateUser
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*
import kotlin.test.assertNull

/**
 * Check class UpdateUser
 */
@ExtendWith(MockitoExtension::class)
class ExistedNumberIdentificationTest {
  private lateinit var user: User
  private val encoder = BCryptPasswordEncoder()

  @Mock
  private lateinit var userRepository: UserRepository

  @Mock
  private lateinit var addressRepository: AddressRepository

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initTest() {
    user = MockUserCreator().create()
    user.password = encoder.encode(user.password)
  }

  /**
   * Check with valid user and a number of identification existed
   */
  @Test
  fun existedNumberIdentification() {
    try {
      val id = user.id!!
      val user2 = MockUserCreator().create()
      user2.id = id + 1
      Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.of(user))
      Mockito.`when`(userRepository.findByUsername(user.username!!)).thenReturn(Optional.of(user))
      Mockito.`when`(userRepository.findByNumberIdentification(user.numberIdentification!!))
        .thenReturn(Optional.of(user2))
      UpdateUser(userRepository, addressRepository, id, user).execute()
      fail()
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, UserMessages.ALREADY_REGISTERED_NUMBER_IDENTIFICATION)
    }
  }
}