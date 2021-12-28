package edu.tpu.ruban.data.repository

import edu.tpu.ruban.data.api.tables.UsersTable
import edu.tpu.ruban.domain.entities.Credentials
import edu.tpu.ruban.domain.entities.User
import edu.tpu.ruban.domain.repository.UserRepository

class UserRepositoryImpl(private var db: UsersTable) : UserRepository {
    override suspend fun getUserMatches(login: String?, isAdmin: Boolean?, isBanned: Boolean?): List<User> {
        return db.getUsersMatching(login, isAdmin, isBanned, strictName = false)
    }

    override suspend fun getUserById(): User? {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByLogin(login: String): User? {
        return db.getUsersMatching(login, strictName = true).firstOrNull()
    }

    override suspend fun createUser(credentials: Credentials) {
        return db.createUser(credentials)
    }

    override suspend fun updateUser(): User {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser() {
        TODO("Not yet implemented")
    }
}