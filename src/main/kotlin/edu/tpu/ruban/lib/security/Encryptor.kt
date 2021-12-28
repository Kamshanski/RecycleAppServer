package edu.tpu.ruban.lib.security

import org.mindrot.jbcrypt.BCrypt

object Encryptor {
    fun passwordHash(pwd: String) : String {
        return BCrypt.hashpw(pwd, BCrypt.gensalt())
    }

    fun verifyPassword(pwd: String, hash: String) : Boolean {
        return BCrypt.checkpw(pwd, hash)
    }
}