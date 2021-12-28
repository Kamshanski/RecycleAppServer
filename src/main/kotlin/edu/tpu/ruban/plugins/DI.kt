package edu.tpu.ruban.plugins

import edu.tpu.ruban.di.applicationModule
import edu.tpu.ruban.di.dataModule
import edu.tpu.ruban.di.domainModule
import edu.tpu.ruban.di.presenterModule
import io.ktor.application.*
import org.koin.core.logger.PrintLogger
import org.koin.ktor.ext.Koin


fun Application.configureDi() {
    install(Koin) {
        logger(PrintLogger())
        modules(dataModule, domainModule, presenterModule, applicationModule)
    }
}