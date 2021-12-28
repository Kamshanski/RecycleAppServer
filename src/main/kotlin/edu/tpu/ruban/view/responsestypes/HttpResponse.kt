package edu.tpu.ruban.view.responsestypes

import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.http.*
import io.ktor.response.*

open class HttpResponse (
    val path: String,
    payload: Map<String, String> = emptyMap(),
    success: Boolean = true,
    httpStatusCode: HttpStatusCode = HttpStatusCode.OK,
    headers: Map<String, String> = emptyMap()
) : BaseResponse(
    payload = payload,
    success = success,
    contentType = ContentType.Application.Json.withCharset(Charsets.UTF_8),
    httpStatusCode = httpStatusCode,
    headers = headers
) {

    override suspend fun sendContext(call: ApplicationCall) {
        call.respond(FreeMarkerContent(template = path, model = payload))
    }
}