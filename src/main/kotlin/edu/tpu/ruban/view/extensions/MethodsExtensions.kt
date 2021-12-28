package edu.tpu.ruban.view.extensions

import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*

suspend inline fun PipelineContext<Unit, ApplicationCall>.safeHttpMethod(crossinline body: PipelineInterceptor<Unit, ApplicationCall>) {
    try {
        body(Unit)
    } catch (ex: Exception) {
        call.respondError(ex)
    }
}

@ContextDsl
inline fun Route.safeGet(crossinline body: PipelineInterceptor<Unit, ApplicationCall>) {
    get {
        safeHttpMethod(body)
    }
}

@ContextDsl
inline fun Route.safePost(crossinline body: PipelineInterceptor<Unit, ApplicationCall>) {
    post {
        safeHttpMethod(body)
    }
}

@ContextDsl
inline fun Route.safeDelete(crossinline body: PipelineInterceptor<Unit, ApplicationCall>) {
    delete {
        safeHttpMethod(body)
    }
}

@ContextDsl
inline fun Route.safePatch(crossinline body: PipelineInterceptor<Unit, ApplicationCall>) {
    patch {
        safeHttpMethod(body)
    }
}