package edu.tpu.ruban.domain.usecase

import edu.tpu.ruban.domain.entities.User
import edu.tpu.ruban.domain.repository.UserRepository

class SelectUsersUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(login: String): List<User> {
        return repository.getUserMatches(login)
    }
}