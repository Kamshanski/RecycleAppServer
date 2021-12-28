package edu.tpu.ruban.view

import edu.tpu.ruban.view.api.auth
import edu.tpu.ruban.view.api.cities
import edu.tpu.ruban.view.api.users
import io.ktor.application.*
import io.ktor.routing.*

fun Application.apiRoutes() = routing {
    route("api/") {
        cities()
        users()
        auth()
    }
}