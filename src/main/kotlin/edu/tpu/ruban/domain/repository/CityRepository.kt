package edu.tpu.ruban.domain.repository

import edu.tpu.ruban.domain.entities.City
import edu.tpu.ruban.domain.entities.Coordinates

interface CityRepository {
    suspend fun getCityById(id: Long, localizationId: Long) : City?
    suspend fun getCityMatching(coordinates: Coordinates?, name: String?, localizationId: Long, limit: Long) : List<City>
}