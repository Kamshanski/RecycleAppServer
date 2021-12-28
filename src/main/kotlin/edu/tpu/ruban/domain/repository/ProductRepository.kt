package edu.tpu.ruban.domain.repository

import edu.tpu.ruban.domain.entities.Product

interface ProductRepository {
    fun getProductsMatches(barcode: String, barcodeType: Int, lang: Int) : List<Product>
}