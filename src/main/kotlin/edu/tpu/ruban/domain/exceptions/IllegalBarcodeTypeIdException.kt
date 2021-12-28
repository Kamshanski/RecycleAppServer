package edu.tpu.ruban.domain.exceptions

class IllegalBarcodeTypeIdException(id: Int) : RuntimeException("BarcodeType of id $id is not present") {
}