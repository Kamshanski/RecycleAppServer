package edu.tpu.ruban.data.converter.domain

import edu.tpu.ruban.domain.entities.BarcodeType
import edu.tpu.ruban.domain.exceptions.IllegalBarcodeTypeIdException

object BarcodeTypeConverter {
    fun Int.asBarcodeType() : BarcodeType {
        val values = BarcodeType.values()
        if (this in values.indices) {
            return values[this]
        }
        throw IllegalBarcodeTypeIdException(id = this)
    }
}