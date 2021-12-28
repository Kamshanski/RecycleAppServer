package edu.tpu.ruban.view.responsestypes

import io.ktor.http.*

class NotImplementedErrorJsonResponse(
    msg: String,
    httpStatusCode: HttpStatusCode = HttpStatusCode.MethodNotAllowed,
    headers: Map<String, String> = emptyMap()
) : JsonResponse(
    payload = msg,
    success = false,
    httpStatusCode = httpStatusCode,
    headers = headers
)