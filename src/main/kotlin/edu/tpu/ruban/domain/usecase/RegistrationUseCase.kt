package edu.tpu.ruban.domain.usecase

import edu.tpu.ruban.customLibs.serverexceptions.AuthException
import edu.tpu.ruban.domain.entities.Credentials
import edu.tpu.ruban.domain.repository.UserRepository

class RegistrationUseCase(
    val repository: UserRepository
) {
    suspend operator fun invoke(credentials: Credentials) {
        val existingUser = repository.getUserByLogin(credentials.login)

        require(existingUser == null) { AuthException("User ${credentials.login} already exists") }

        repository.createUser(credentials)
    }
}