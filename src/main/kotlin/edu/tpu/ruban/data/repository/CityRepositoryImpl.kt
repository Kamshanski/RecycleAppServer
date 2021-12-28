package edu.tpu.ruban.data.repository

import edu.tpu.ruban.data.api.tables.CitiesTable
import edu.tpu.ruban.domain.entities.City
import edu.tpu.ruban.domain.entities.Coordinates
import edu.tpu.ruban.domain.repository.CityRepository

class CityRepositoryImpl(private val db: CitiesTable) : CityRepository {
    override suspend fun getCityById(id: Long, localizationId: Long): City? =
        db.getCityById(id, localizationId)

    override suspend fun getCityMatching(coordinates: Coordinates?, name: String?, localizationId: Long, limit: Long): List<City> =
        db.getCitiesMatches(name, coordinates, localizationId, limit)
}