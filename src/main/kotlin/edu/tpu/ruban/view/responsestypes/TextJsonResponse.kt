package edu.tpu.ruban.view.responsestypes

import io.ktor.http.*

class TextJsonResponse(
    msg: String? = null,
    httpStatusCode: HttpStatusCode = HttpStatusCode.InternalServerError,
    headers: Map<String, String> = emptyMap()
) : JsonResponse (
    payload = msg,
    success = true,
    httpStatusCode = httpStatusCode,
    headers = headers
)