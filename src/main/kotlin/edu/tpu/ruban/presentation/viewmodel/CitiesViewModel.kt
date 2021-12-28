package edu.tpu.ruban.presentation.viewmodel

import edu.tpu.ruban.domain.entities.City
import edu.tpu.ruban.domain.entities.Coordinates
import edu.tpu.ruban.presentation.io.GetCitiesResponse
import edu.tpu.ruban.domain.usecase.GetCityByIdUseCase
import edu.tpu.ruban.domain.usecase.GetCityMatchingUseCase
import edu.tpu.ruban.lib.basic.toList

class CitiesViewModel(
    private val getCityByIdUseCase: GetCityByIdUseCase,
    private val getCityMatchingUseCase: GetCityMatchingUseCase,
    private val localizationId: Long = 1
) {
    suspend fun getCityBy(coordinates: Coordinates?, name: String?, localizationId: Long, limit: Long = 0) =
        cityResponse(getCityMatchingUseCase(coordinates, name, localizationId, limit))

    suspend fun getCityById(id: Long) =
        cityResponse(getCityByIdUseCase(id, localizationId)?.toList() ?: emptyList())

    private fun cityResponse(cities: List<City>) = GetCitiesResponse(cities)


}
