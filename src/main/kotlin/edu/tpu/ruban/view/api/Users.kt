package edu.tpu.ruban.view.api

import edu.tpu.ruban.di.inject
import edu.tpu.ruban.domain.entities.Credentials
import edu.tpu.ruban.presentation.viewmodel.UserViewModel
import edu.tpu.ruban.view.extensions.respondError
import edu.tpu.ruban.view.extensions.respondSuccess
import edu.tpu.ruban.view.extensions.respondNotImplementedYet
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*


fun Route.users() = route("users") {
    // get user data
    get {
        call.respondNotImplementedYet()
    }
    // registration
    post {
        try {
            val credentials = call.receive<Credentials>()

            val vm by inject<UserViewModel>()

            vm.register(credentials)

            call.respondSuccess("User ${credentials.login} successfully created")
        } catch (ex: Exception) {
            call.respondError(ex)
        }
    }

    // modify user
    patch {
        call.respondNotImplementedYet()
    }



}