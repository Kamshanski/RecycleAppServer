package edu.tpu.ruban.data.repository

import edu.tpu.ruban.domain.entities.Product
import edu.tpu.ruban.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val db:
) : ProductRepository {
    override fun getProductsMatches(barcode: String, barcodeType: Int, lang: Int): List<Product> {
        TODO("Not yet implemented")
    }
}