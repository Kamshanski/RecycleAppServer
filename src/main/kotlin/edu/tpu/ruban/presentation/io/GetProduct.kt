package edu.tpu.ruban.presentation.io

import edu.tpu.ruban.domain.entities.Product

class GetProductRequest(
    val barcode: String,
    val barcodeType: Int,
    val languageId: Int,
)

class GetProductResponse(
    val products: List<Product>,
    val relatedProducts: List<Product> = emptyList()
)