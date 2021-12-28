package edu.tpu.ruban.domain.usecase

import edu.tpu.ruban.domain.entities.City
import edu.tpu.ruban.domain.entities.Coordinates
import edu.tpu.ruban.domain.repository.CityRepository

class GetCityMatchingUseCase(
    private val repository: CityRepository
) {
    suspend operator fun invoke(coordinates: Coordinates?, name: String?, localizationId: Long, limit: Long): List<City> {
        return repository.getCityMatching(coordinates, name, localizationId, limit)
    }
}