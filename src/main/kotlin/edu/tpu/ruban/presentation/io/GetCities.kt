package edu.tpu.ruban.presentation.io

import edu.tpu.ruban.domain.entities.City
import edu.tpu.ruban.domain.entities.Coordinates

data class GetCitiesRequest(
    val id: Long?,
    val name: String?,
    val coordinates: Coordinates?,
    val localizationId: Long,
    val limit: Long,
)

data class GetCitiesResponse(
    val cities: List<City>
)