package es.uniovi.apuntesuniovi.mocks.dtos

import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.models.types.IdentificationType
import es.uniovi.apuntesuniovi.models.types.LanguageType
import es.uniovi.apuntesuniovi.models.types.RoleType
import java.time.LocalDate

/**
 * Service to create mock data of the dto UserDto
 */
class MockUserDtoCreator : MockCreator<UserDto> {
    override fun create(): UserDto {
        return UserDto(
            id = 3,
            name = "name",
            surname = "surname",
            active = true,
            birthDate = LocalDate.now(),
            email = "admin@admin.com",
            identificationType = IdentificationType.DNI.toString(),
            img = null,
            numberIdentification = "16207928N",
            password = "admin",
            phone = "623548956",
            username = "admin",
            role = RoleType.ROLE_ADMIN.toString(),
            address = MockAddressDtoCreator().create(),
            language = LanguageType.ES.toString()
        )
    }
}