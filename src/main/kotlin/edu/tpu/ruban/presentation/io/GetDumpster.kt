package edu.tpu.ruban.presentation.io

import edu.tpu.ruban.domain.entities.DumpsterPoint

class GetDumpsterInput(
    val productTypeId: Int,
    val cityId: Int,
    val languageId: Int
)

class GetDumpsterOutput(
    val productTypeId: Int,
    val dumpsters: List<DumpsterPoint>,
)