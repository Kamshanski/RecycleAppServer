package edu.tpu.ruban.plugins

import edu.tpu.ruban.data.datasource.database.Database
import io.ktor.application.*

fun Application.configurePostgresql() {
    Database.initialize()
}