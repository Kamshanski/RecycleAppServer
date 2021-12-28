package edu.tpu.ruban.data.managers

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import edu.tpu.ruban.data.api.AuthManager
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import java.util.*


class AuthManagerImpl(
    private val audience: String,
    private val issuer: String,
    private val subject: String,
    private val secret: String,
) : AuthManager {
    companion object {
        const val IS_ADMIN_CLAIM = "isAdmin"
        const val USER_ID_CLAIM = "userId"
//        private const val cleanUpDelay: Long = 60L * 60L * 1000L // 1h
        private const val tokenLiveTime = 15L * 60L * 60L * 1000L // 15h
    }

//    private val threadPool = Executors.newSingleThreadExecutor()
//    private val tokenScope = CoroutineScope(SupervisorJob() + threadPool.asCoroutineDispatcher())

    @Volatile
    private var isScheduled = false

    // Expiration is checked automatically
    private val verifierBase = JWT
        .require(Algorithm.HMAC512(secret))
        .withAudience(audience)
        .withSubject(subject)
        .withIssuer(issuer)

//    private val pool = TimerMapPoolImpl<String, Profile>() // JWT to user_id

    override fun grantToken(userId: Long, isAdmin: Boolean): String =
        JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withSubject(subject)
            .withClaim(IS_ADMIN_CLAIM, isAdmin)
            .withClaim(USER_ID_CLAIM, userId)
            .withExpiresAt(Date(System.currentTimeMillis() + tokenLiveTime))
            .sign(Algorithm.HMAC512(secret))

    override fun buildVerifier(): JWTVerifier = verifierBase.build()


    override fun validate(credential: JWTCredential): Principal? =
        if (credential.payload.getClaim(USER_ID_CLAIM)?.asString().isNullOrBlank())
            JWTPrincipal(credential.payload)
        else
            null

    override fun errorHandler(defaultScheme: String, realm: String) {
        print("Cannot login into $realm with scheme $defaultScheme")
    }

//    override fun getProfile(token: String) = pool.get(token)
//
//    override fun scheduleCleanup() {
//        if (!isScheduled) {
//            synchronized(this) {
//                if (!isScheduled) {
//                    startCleanupCoroutine()
//                }
//            }
//        }
//    }
//
//    private fun startCleanupCoroutine() {
//        tokenScope.launch {
//            delay(cleanUpDelay)
//            if (pool.size > 10) {
//                pool.evictExpired()
//            }
//        }.invokeOnCompletion { scheduleCleanup() } // must be on all time
//    }
}