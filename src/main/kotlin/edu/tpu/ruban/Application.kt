package edu.tpu.ruban

import io.ktor.application.*
import edu.tpu.ruban.controller.website.configureWebsiteRouting
import edu.tpu.ruban.controller.recycleapi.configureRecycleApiRouting
import edu.tpu.ruban.controller.auth.configureAuthRouting
import edu.tpu.ruban.plugins.*
import edu.tpu.ruban.view.apiRoutes

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    // DI must be first forever
    configureDi()

    // plugins
    configureSerialization()
    configureSecurity()
    configureHtmlTemplating()

    // data sources
    configurePostgresql()

    // routes
    apiRoutes()

    // application
    configureRecycleApiRouting()
    configureAuthRouting()
    configureWebsiteRouting()
}
