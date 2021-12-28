package edu.tpu.ruban.plugins

import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.client.request.forms.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
            serializeNulls()
        }
    }
}
