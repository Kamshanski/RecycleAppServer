package edu.tpu.ruban.view.api

import edu.tpu.ruban.di.inject
import edu.tpu.ruban.domain.entities.Credentials
import edu.tpu.ruban.presentation.viewmodel.AuthViewModel
import edu.tpu.ruban.view.extensions.respondError
import edu.tpu.ruban.view.extensions.respondSuccess
import edu.tpu.ruban.view.extensions.safeGet
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*


fun Route.auth() = route("auth") {
    // get Token
    safeGet {
        val credentials = call.receive<Credentials>()

        val vm by inject<AuthViewModel>()

        val response = vm.getTokenFor(credentials)

        call.respondSuccess(response)
    }
}