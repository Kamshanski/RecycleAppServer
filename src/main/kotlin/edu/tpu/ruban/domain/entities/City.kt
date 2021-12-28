package edu.tpu.ruban.domain.entities

data class City(
    val id_city: Int,
    val id_country: Int,
    val coordinates: Coordinates,
    val name: String
)