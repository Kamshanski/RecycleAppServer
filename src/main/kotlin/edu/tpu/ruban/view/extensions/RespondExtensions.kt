package edu.tpu.ruban.view.extensions

import edu.tpu.ruban.view.responsestypes.*
import io.ktor.application.*

// base
public suspend fun ApplicationCall.respondWith(response: BaseResponse) = response.sendTo(this)


// success
public suspend fun ApplicationCall.respondPage(response: HttpResponse) = respondWith(response)

public suspend fun ApplicationCall.respondSuccess(payload: Any?) = respondWith(JsonResponse(payload = payload))

public suspend fun ApplicationCall.respondSuccessText(msg: String? = null) = respondWith(TextJsonResponse(msg = msg))


// failure
public suspend fun ApplicationCall.respondError(error: Throwable) = respondWith(ErrorJsonResponse(error = error))

public suspend fun ApplicationCall.respondNotImplementedYet(msg: String = "") = respondWith(NotImplementedErrorJsonResponse(msg))