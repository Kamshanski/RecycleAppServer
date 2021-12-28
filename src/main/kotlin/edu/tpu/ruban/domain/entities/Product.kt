package edu.tpu.ruban.domain.entities

// TODO: ТИПОВ ПРОДУКТА НЕСКОЛЬКО НА ОДИН ПРОДУКТ!!! МОЖНО БИЛДЕР
class Product(
    val id: Long,
    val name: String,
    val barcode: String,
    val barcodeType: BarcodeType,
    val productTypeId: Long,
    val productTypeName: String,
    val info: String,
    val link: String?,
)