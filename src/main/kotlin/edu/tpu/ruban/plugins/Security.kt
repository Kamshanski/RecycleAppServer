package edu.tpu.ruban.plugins

import edu.tpu.ruban.data.api.AuthManager
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

const val JWT_AUTH = "auth-jwt"

fun Application.configureSecurity() {

    authentication {

        val authManager by inject<AuthManager>()

        jwt(JWT_AUTH) {
            this.realm = environment.config.property("jwt.realm").getString()
            verifier(authManager.buildVerifier())
            validate { credential -> authManager.validate(credential) }
            challenge { defaultScheme, realm -> authManager.errorHandler(defaultScheme, realm) }
        }
    }
}

fun ApplicationCall.jwtPrincipal(): JWTPrincipal? = authentication.principal()

val JWTPrincipal.isAdmin : Boolean
    get() = get("isAdmin")?.toBoolean() ?: false

fun Route.authenticateViaJwt(
    optional: Boolean = false,
    build: Route.() -> Unit
) = authenticate(JWT_AUTH, optional = optional, build = build)