package edu.tpu.ruban.domain.usecase

import edu.tpu.ruban.domain.entities.User
import edu.tpu.ruban.domain.repository.UserRepository

class SelectUserByLoginUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(login: String): User? {
        return repository.getUserByLogin(login)
    }
}