package edu.tpu.ruban.data.api.tables

import edu.tpu.ruban.domain.entities.Credentials
import edu.tpu.ruban.domain.entities.User

interface UsersTable {
    suspend fun getUsersMatching(login: String? = null, admin: Boolean? = null, banned: Boolean? = null, strictName: Boolean = false) : List<User>
    suspend fun createUser(credentials: Credentials)

}