package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.UserAssembler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

/**
 * Check class UserService
 */
@ExtendWith(MockitoExtension::class)
class FindByIdTest {
  private lateinit var userService: UserService

  @Mock
  private lateinit var userRepository: UserRepository

  @Mock
  private lateinit var addressRepository: AddressRepository
  private val userAssembler = UserAssembler()

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
    val id = 1L
    val user = MockUserCreator().create()
    user.id = id
    val userDto = userAssembler.entityToDto(user)
    Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.of(user))
    val result = userService.findById(id)
    assertEquals(userDto, result)
    assertEquals(user.id, result.id)
    assertNotNull(result.img)
    assertNotNull(result.password)
  }
}