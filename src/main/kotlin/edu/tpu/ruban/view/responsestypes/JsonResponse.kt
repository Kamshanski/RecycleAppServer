package edu.tpu.ruban.view.responsestypes

import com.google.gson.Gson
import edu.tpu.ruban.data.converter.gson.CommonGson
import io.ktor.application.*
import io.ktor.content.*
import io.ktor.http.*
import io.ktor.response.*


open class JsonResponse(
    payload: Any? = null,
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
        call.respond(TextContent(text = responseGson.toJson(this), contentType))
    }

    companion object {
        val responseGson: Gson = CommonGson.commonGson
    }
}