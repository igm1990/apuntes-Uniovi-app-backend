package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class IdentificationTypeTest {
    private lateinit var user: User

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initData() {
        user = MockFactory().getEntities().createUser()
    }

    @Test
    fun validIdentificationType() {
        user.setIdentificationType("DNI")
        assertEquals(IdentificationType.DNI, user.identificationType)
    }

    @Test
    fun invalidIdentificationType() {
        try {
            user.setIdentificationType("No exists")
            fail("IdentificationType is invalid")
        } catch (e: IllegalArgumentException) {
            assertEquals(IdentificationType.NIE, user.identificationType)
            assertEquals(e.message, ExceptionMessages.INVALID_IDENTIFICATION_TYPE)
        }
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyIdentificationType() {
        try {
            user.setIdentificationType("")
            fail("IdentificationType can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(IdentificationType.NIE, user.identificationType)
            assertEquals(e.message, ExceptionMessages.INVALID_IDENTIFICATION_TYPE)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullIdentificationType() {
        try {
            user.setIdentificationType(null)
            fail("IdentificationType can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(IdentificationType.NIE, user.identificationType)
            assertEquals(e.message, ExceptionMessages.NULL_IDENTIFICATION_TYPE)
        }
    }
}