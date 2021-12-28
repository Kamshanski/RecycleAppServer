package edu.tpu.ruban.data.api.tables

import edu.tpu.ruban.domain.entities.City
import edu.tpu.ruban.domain.entities.Coordinates

interface CitiesTable {
    suspend fun getCitiesMatches(
        name: String? = null,
        coordinates: Coordinates? = null,
        localizationId: Long,
        limit: Long,   // all
    ) : List<City>

    suspend fun getCityById(id: Long, localizationId: Long) : City?



}