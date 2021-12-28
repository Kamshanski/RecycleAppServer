package edu.tpu.ruban.data.datasource.database

import edu.tpu.ruban.customLibs.serverexceptions.RawQueryReturnedNullResultException
import edu.tpu.ruban.data.datasource.database.Database.queryExpose
import edu.tpu.ruban.data.datasource.database.tables.CountryTable
import edu.tpu.ruban.data.datasource.database.tables.UserTable
import edu.tpu.ruban.data.datasource.database.tables.UserTable.toUser
import edu.tpu.ruban.domain.entities.DumpsterPoint
import edu.tpu.ruban.domain.entities.Product
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.postgresql.geometric.PGpoint

class DatabaseHelper {
    suspend fun getCountries() = queryExpose {
        CountryTable.selectAll().map { it[CountryTable.isoCode] }
    }

    suspend fun addUser(login: String, hash: String) = queryExpose {
        UserTable.insert { table ->
            table[UserTable.login] = login
            table[UserTable.hash] = hash
        }
    }

    @Throws(IllegalArgumentException::class)
    suspend fun findUser(login: String? = null, id: Int? = null) = queryExpose {
        val q: Op<Boolean> = when {
            login != null -> UserTable.login eq login
            id != null -> UserTable.id eq id
            else -> throw IllegalArgumentException("one of params (id or login) must be non-null")
        }

        UserTable.select(q)
            .map { it.toUser() }
            .singleOrNull()
    }

    suspend fun getAllUsers() = queryExpose {
        UserTable.selectAll().map { it.toUser()!! }
    }


    suspend fun deleteUser(login: String) = queryExpose {
        UserTable.deleteWhere { UserTable.login.eq(login) }
    }


    class ProductBuilder(
        val productId: Int,
        val name: String,
        val productInfo: String,
        val productTypes: MutableMap<Int, String> = mutableMapOf()
    )
    // TODO: заменить barcodeType на Enum, вынести билдер
    suspend fun getProductInfo(barcode: String, barcodeType: Int, langId: Int) : List<Product> {
        val query = """
                SELECT product.id_product, name, product_info, A.id_product_type, B.txt FROM "product"
                    INNER JOIN "product_typing" A ON product.id_product = A.id_product
                    INNER JOIN localization."product_type_l" B ON A.id_product_type = B.id_product_type
                WHERE barcode = '$barcode' AND id_barcode_type = $barcodeType AND lang = $langId
            """.trimIndent()

        val builders = Database.rawSelect(query) { resultSet ->

            val products = mutableMapOf<Int, ProductBuilder>()

            with(resultSet) {
                while (next()) {
                    products.computeIfAbsent(getInt("id_product")) { key ->
                        ProductBuilder(key,
                                       getString("name"),
                                       getString("product_info"))
                    }.productTypes[getInt("id_product_type")] = getString("txt")
                }
            }
            products
        } ?: throw RawQueryReturnedNullResultException(query)

        val products = builders.map { (_, builder) ->
            Product(builder.productId, builder.name, builder.productInfo, builder.productTypes)
        }
        return products
    }

    suspend fun getDumpsters(productTypeId: Int, cityId: Int, langId: Int) : List<DumpsterPoint> {
        val query = """
                SELECT D.id_dumpster, D."name", D.coordinates, locDT.txt FROM "city"
                    INNER JOIN "local_rule" LR ON LR.id_city = city.id_city
                    INNER JOIN "rule" R ON R.id_collector = LR.id_collector
                    INNER JOIN "product_marks" PM ON PM.id_waste = R.id_waste
                    INNER JOIN "dumpster" D ON D.id_collector = R.id_collector
                    INNER JOIN localization.dumpster_type_l locDT ON locDT.id_dumpster_type = D.id_dumpster_type
                WHERE id_product_type = $productTypeId AND city.id_city = $cityId AND locDT.lang = $langId
            """.trimIndent()

        return Database.rawSelect(query) { resultSet ->
            mutableListOf<DumpsterPoint>().also { dumpsterList ->
                while (resultSet.next()) {
                    val dumpsterId = resultSet.getInt("id_dumpster")
                    val name = resultSet.getString("name")
                    val coordinates = resultSet.getObject("coordinates", PGpoint::class.java)
                    val dumpsterTypeLocalized = resultSet.getString("txt")
                    dumpsterList.add(DumpsterPoint(dumpsterId, name, coordinates, dumpsterTypeLocalized))
                }
            }
        } ?: throw RawQueryReturnedNullResultException(query)
    }

}