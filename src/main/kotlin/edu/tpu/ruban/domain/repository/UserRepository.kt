package edu.tpu.ruban.domain.repository

import edu.tpu.ruban.domain.entities.Credentials
import edu.tpu.ruban.domain.entities.User

interface UserRepository {
    suspend fun getUserMatches(login: String? = null, isAdmin: Boolean? = null, isBanned: Boolean? = null) : List<User>
    suspend fun getUserById() : User?
    suspend fun getUserByLogin(login: String) : User?
    suspend fun createUser(credentials: Credentials)
    suspend fun updateUser() : User
    suspend fun deleteUser()
}