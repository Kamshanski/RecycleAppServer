package edu.tpu.ruban.data.datasource.database.rawtables

import edu.tpu.ruban.data.api.tables.ProductsTable
import edu.tpu.ruban.data.datasource.database.Database
import edu.tpu.ruban.data.utils.appendLimitOrNone
import edu.tpu.ruban.data.utils.appendWhere
import edu.tpu.ruban.domain.entities.Product
import edu.tpu.ruban.lib.basic.createList

class ProductsTableImpl : ProductsTable {
    override fun getProductsMatching(barcode: String?, barcodeType: Int?, localizationId: Int, limit: Int, strictName: Boolean): List<Product> {
        val queryBeginning = """
            SELECT
                "goods".products.product_id,
                "goods".products.barcode,
                "goods".barcode_types.barcode_type,
                "localization".local_product_types.product_type_id,
                "localization".local_product_types.product_type_translation,
                "goods".products.product_name,
                "goods".products.info,
                "goods".products.link
            FROM "goods"."products"
                INNER JOIN "goods"."product_typing" ON "goods"."product_typing"."product_id" = "goods"."products"."product_id"
                INNER JOIN "localization".local_product_types ON localization.local_product_types.product_type_id = goods.product_typing.product_type_id AND localization.local_product_types.lang = ${localizationId.coerceAtLeast(1)}
                INNER JOIN "goods".barcode_types ON goods.barcode_types.barcode_type_id = goods.products.barcode_type_id
            WHERE  AND 
            ${if (limit > 0) "LIMIT $limit" else ""}
        """

        val conditions = createList<String> {
            add(""" localization.local_product_types.lang = ${localizationId.coerceAtLeast(1)} """)

            if (barcode != null) {
                add(""" "goods"."products".barcode ${if (strictName) " = " else " LIKE "} '%$barcodeType%' """)
            }

            if (barcodeType != null && barcodeType > 0) {
                add(""" "goods"."products"."barcode_type_id" = $barcodeType """)
            }
        }

        val query = StringBuilder(queryBeginning)
            .append(queryBeginning)
            .appendWhere(conditions)
            .appendLimitOrNone(limit)
            .toString()

        Database.rawSelect(query, )
    }
}