package edu.tpu.ruban.domain.usecase

import edu.tpu.ruban.domain.entities.City
import edu.tpu.ruban.domain.repository.CityRepository

class GetCityByIdUseCase(
    private val repository: CityRepository
) {
    suspend operator fun invoke(id: Long, localizationId: Long): City? {
        return repository.getCityById(id, localizationId)
    }
}