package edu.tpu.ruban.data.converter.domain

import edu.tpu.ruban.domain.entities.Coordinates
import org.postgresql.geometric.PGpoint

object CoordinatesConverter {
    fun PGpoint.asCoordinates() = Coordinates(x, y)
    fun Coordinates.asPgPoint() = PGpoint(latitude, longitude)
}