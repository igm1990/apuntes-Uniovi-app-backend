package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.UserAssembler
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull

/**
 * Check class UserService
 */
@ExtendWith(MockitoExtension::class)
class CreateTest {
  @Mock
  private lateinit var userRepository: UserRepository

  @Mock
  private lateinit var addressRepository: AddressRepository
  private val userAssembler = UserAssembler()

  private lateinit var userService: UserService

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initTest() {
    userService = UserService(userRepository, addressRepository, userAssembler)
  }

  /**
   * Checks the functionality with valid data
   */
  @Test
  fun validData() {
    val user = MockUserCreator().create()
    val userDto = userAssembler.entityToDto(user)
    Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(user)
    val result = userService.create(userDto)
    assertNotEquals(userDto, result)
    assertEquals(user.id, result.id)
    assertNull(result.img)
    assertNull(result.password)
  }
}