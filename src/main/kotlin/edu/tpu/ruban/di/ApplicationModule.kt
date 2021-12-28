package edu.tpu.ruban.di

import edu.tpu.ruban.data.api.AuthManager
import edu.tpu.ruban.data.managers.AuthManagerImpl
import org.koin.dsl.module

val applicationModule = module {
    single<AuthManager> {
        val secret = System.getenv("JWT_SECRET")
        val issuer = System.getenv("JWT_ISSUER")
        val subject = System.getenv("JWT_SUBJECT")
        val audience = System.getenv("JWT_AUDIENCE")
        AuthManagerImpl(audience = audience, issuer = issuer, subject = subject, secret = secret)
    }
}