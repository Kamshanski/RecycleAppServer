package edu.tpu.ruban.domain.entities

import org.postgresql.geometric.PGpoint

class DumpsterPoint(
    val id: Int,
    val name: String?,
    val coordinates: PGpoint,
    val dumpsterType: String
)