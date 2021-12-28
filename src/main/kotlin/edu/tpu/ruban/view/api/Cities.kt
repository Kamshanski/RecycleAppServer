package edu.tpu.ruban.view.api

import edu.tpu.ruban.di.inject
import edu.tpu.ruban.domain.entities.Coordinates
import edu.tpu.ruban.presentation.viewmodel.CitiesViewModel
import edu.tpu.ruban.view.extensions.respondError
import edu.tpu.ruban.view.extensions.respondSuccess
import edu.tpu.ruban.view.extensions.safeGet
import io.ktor.application.*
import io.ktor.routing.*
import org.koin.core.parameter.parametersOf


fun Route.cities() = route("cities") {
    safeGet {
        val queryParams = call.request.queryParameters

        val id = queryParams["id"]?.toLong()
        val name = queryParams["name"]
        val latitude = queryParams["lat"]?.toDouble()
        val longitude = queryParams["lng"]?.toDouble()
        val localizationId = queryParams["lang"]?.toLong()?.coerceAtLeast(1) ?: 1
        val limit = queryParams["limit"]?.toLong()?.coerceAtLeast(0) ?: 0
        val vm : CitiesViewModel by inject { parametersOf(localizationId) }

        val response = when {
            id != null -> vm.getCityById(id)
            else -> {
                val coordinates = latitude?.let { longitude?.let { Coordinates(latitude, longitude) } }
                vm.getCityBy(coordinates, name, localizationId, limit)
            }
        }

        call.respondSuccess(response)
    }
}

