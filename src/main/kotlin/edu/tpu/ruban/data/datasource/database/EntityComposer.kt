package edu.tpu.ruban.data.datasource.database

import edu.tpu.ruban.data.converter.domain.BarcodeTypeConverter.asBarcodeType
import edu.tpu.ruban.data.converter.domain.CoordinatesConverter.asCoordinates
import edu.tpu.ruban.domain.entities.City
import edu.tpu.ruban.domain.entities.Product
import edu.tpu.ruban.domain.entities.User
import edu.tpu.ruban.lib.basic.createList
import org.postgresql.geometric.PGpoint
import java.sql.ResultSet

object EntityComposer {

    fun String.toPgString() = "'$this'"

    fun buildCitiesFromResultSet(resultSet: ResultSet) : List<City> =
        createList {
            while (resultSet.next()) {
                val cityId = resultSet.getInt("city_id")
                val countryId = resultSet.getInt("country_id")
                val coordinates = resultSet.getObject("coordinates", PGpoint::class.java).asCoordinates()
                val name = resultSet.getString("city_translation")
                add(City(cityId, countryId, coordinates, name))
            }
        }

    fun buildUsersFromResultSet(resultSet: ResultSet) : List<User> =
        createList {
            while (resultSet.next()) {
                val userId = resultSet.getLong("user_id")
                val login = resultSet.getString("login")
                val hash = resultSet.getString("hash")
                val isAdmin = resultSet.getBoolean("admin")
                val isBanned = resultSet.getBoolean("ban")
                add(User(userId, login, hash, isAdmin, isBanned))
            }
        }

    fun buildProductsFromResultSet(resultSet: ResultSet) : List<Product> =
        createList {
            while (resultSet.next()) {
                val productId = resultSet.getLong("product_id")
                val barcode = resultSet.getString("barcode")
                val barcodeType = resultSet.getInt("barcode_type")
                val name = resultSet.getString("product_name")
                val productTypeId = resultSet.getLong("product_type_id")
                val productTypeName = resultSet.getString("product_type_translation")
                val info = resultSet.getString("info")
                val link = resultSet.getString("link")
                add(Product(productId, name, barcode, barcodeType.asBarcodeType(), productTypeId, productTypeName, info, link))
            }
        }
}