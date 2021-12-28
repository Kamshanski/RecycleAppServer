package edu.tpu.ruban.domain.repository

import edu.tpu.ruban.domain.entities.User

interface AuthRepository {
    fun grantToken(user: User) : String
}