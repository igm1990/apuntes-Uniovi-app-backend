package es.uniovi.apuntesuniovi.controllers.commands.users

import es.uniovi.apuntesuniovi.controllers.commands.BaseFindAll
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto
import org.springframework.data.domain.Pageable

/**
 * Return all users in controller layer
 */
class FindAllUsers(
    userService: UserService,
    pageable: Pageable
) : BaseFindAll<User, UserDto>(userService, pageable)