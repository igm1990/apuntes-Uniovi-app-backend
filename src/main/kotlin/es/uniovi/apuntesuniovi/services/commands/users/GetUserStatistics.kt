package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.statistics.UserStatistics

class GetUserStatistics(
    private val userRepository: UserRepository
) : AbstractCommand<UserStatistics>() {

    override fun execute(): UserStatistics {
        logService.info("execute() - start")
        val userStatistics = UserStatistics(
            active = userRepository.countByActive(true),
            inactive = userRepository.countByActive(false),
            numAdmin = userRepository.countByRole(RoleType.ROLE_ADMIN),
            numStudents = userRepository.countByRole(RoleType.ROLE_STUDENT),
            numTeachers = userRepository.countByRole(RoleType.ROLE_TEACHER)
        )
        logService.info("execute() - end")
        return userStatistics
    }
}