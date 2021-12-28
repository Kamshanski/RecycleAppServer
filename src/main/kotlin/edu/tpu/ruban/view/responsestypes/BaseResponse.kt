package edu.tpu.ruban.view.responsestypes


import io.ktor.application.*
import io.ktor.content.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.utils.io.charsets.*

// simple response that returns Json
open class BaseResponse(
    val payload: Any? = null,
    val success: Boolean = true,

    @Transient
    val contentType: ContentType = ContentType.Text.Plain.withCharset(Charsets.UTF_8),
    @Transient
    val httpStatusCode: HttpStatusCode = HttpStatusCode.OK,
    @Transient
    val headers: Map<String, String> = emptyMap()
) {
    suspend fun sendTo(call: ApplicationCall) {
        configureResponse(call)
        sendContext(call)
    }


    private suspend fun configureResponse(call: ApplicationCall) {
        for ((header, value) in headers) {
            if (header != HttpHeaders.ContentType) {
                call.response.headers.append(header, value)
            }
        }
    }


    open suspend fun sendContext(call: ApplicationCall) {
        call.respond(TextContent(text = this.toString(), contentType))
    }


    override fun toString(): String {
        return "success=$success, payload=$payload"
    }
}