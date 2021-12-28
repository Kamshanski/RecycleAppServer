package edu.tpu.ruban.presentation.viewmodel

import edu.tpu.ruban.domain.entities.Credentials
import edu.tpu.ruban.domain.usecase.RegistrationUseCase
import edu.tpu.ruban.domain.usecase.SelectUsersUseCase

class UserViewModel(
    private val registerUseCase: RegistrationUseCase,
    private val selectUseCase: SelectUsersUseCase
) {
    suspend fun register(credentials: Credentials) {
        return registerUseCase(credentials)
    }
}