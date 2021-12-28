package edu.tpu.ruban.data.repository

import edu.tpu.ruban.data.api.AuthManager
import edu.tpu.ruban.domain.entities.User
import edu.tpu.ruban.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authManager: AuthManager
) : AuthRepository {
    override fun grantToken(user: User): String =
        authManager.grantToken(user.id, user.isAdmin)
}