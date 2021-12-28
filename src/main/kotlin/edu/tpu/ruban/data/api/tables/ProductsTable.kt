package edu.tpu.ruban.data.api.tables

import edu.tpu.ruban.domain.entities.Product

interface ProductsTable {
    fun getProductsMatching(barcode: String?, barcodeType: Int?, localizationId: Int, limit: Int = 0, strictBarcode: Boolean) : List<Product>
}