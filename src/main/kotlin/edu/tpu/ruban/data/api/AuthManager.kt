package edu.tpu.ruban.data.api

import com.auth0.jwt.JWTVerifier
import io.ktor.auth.*
import io.ktor.auth.jwt.*

interface AuthManager {
//    fun scheduleCleanup()
//    fun getProfile(token: String) : Profile?
    fun grantToken(userId: Long, isAdmin: Boolean) : String
    fun buildVerifier() : JWTVerifier
    fun validate(credential: JWTCredential) : Principal?
    fun errorHandler(defaultScheme: String, realm: String)
}