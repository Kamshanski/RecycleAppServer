package edu.tpu.ruban.view.responsestypes

import io.ktor.http.*

class ErrorPayload(val clazz: String?, val message: String? = null, val stackTrace: String? = null) {
    constructor(error: Throwable) : this(error::class.simpleName, error.message, error.stackTraceToString())
}

class ErrorJsonResponse(
    error: Throwable,
    httpStatusCode: HttpStatusCode = HttpStatusCode.InternalServerError,
    headers: Map<String, String> = emptyMap()
) : JsonResponse(
    payload = ErrorPayload(error),
    success = false,
    httpStatusCode = httpStatusCode,
    headers = headers
)