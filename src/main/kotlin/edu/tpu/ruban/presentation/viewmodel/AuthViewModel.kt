package edu.tpu.ruban.presentation.viewmodel

import edu.tpu.ruban.domain.entities.Credentials
import edu.tpu.ruban.domain.scenario.GrantTokenScenario
import edu.tpu.ruban.presentation.io.GetTokenResponse

class AuthViewModel(
    private val grantTokenScenario: GrantTokenScenario
) {

    suspend fun getTokenFor(credentials: Credentials) : GetTokenResponse {
        return GetTokenResponse(grantTokenScenario.invoke(credentials))
    }
}