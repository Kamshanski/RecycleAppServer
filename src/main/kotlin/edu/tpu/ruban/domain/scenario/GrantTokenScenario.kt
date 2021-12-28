package edu.tpu.ruban.domain.scenario

import edu.tpu.ruban.customLibs.serverexceptions.AuthException
import edu.tpu.ruban.domain.entities.Credentials
import edu.tpu.ruban.domain.repository.AuthRepository
import edu.tpu.ruban.domain.usecase.SelectUserByLoginUseCase

class GrantTokenScenario(
    private val selectUserByLoginUseCase: SelectUserByLoginUseCase,
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(credentials: Credentials): String {
        val user = selectUserByLoginUseCase(credentials.login)

        requireNotNull(user) { AuthException("User ${credentials.login} is not registered") }

        return authRepository.grantToken(user)
    }
}