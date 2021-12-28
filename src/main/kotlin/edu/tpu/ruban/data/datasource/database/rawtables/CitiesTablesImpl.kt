package edu.tpu.ruban.data.datasource.database.rawtables

import edu.tpu.ruban.data.api.tables.CitiesTable
import edu.tpu.ruban.data.converter.domain.CoordinatesConverter.asPgPoint
import edu.tpu.ruban.data.datasource.database.Database
import edu.tpu.ruban.data.datasource.database.EntityComposer
import edu.tpu.ruban.domain.entities.City
import edu.tpu.ruban.domain.entities.Coordinates

class CitiesTablesImpl : CitiesTable {
    /*
    SELECT * FROM "places"."cities"
        INNER JOIN "localization"."local_cities" A ON A.lang = 1 AND "localization"."city_id" = A.city_id AND A."city_translation" = 'Томск'
    WHERE "cities"."city_id" = 1
    ORDER BY "cities".coordinates <-> point '(22.0,22.2)'
    LIMIT 1
     */
    override suspend fun getCitiesMatches(name: String?, coordinates: Coordinates?, localizationId: Long, limit: Long): List<City> {
        val langId = localizationId.coerceAtLeast(1)

        val sb = StringBuilder("""
            SELECT 
               "places"."cities"."city_id",
               "places"."cities"."country_id",
               "places"."cities"."coordinates",
               "localization"."local_cities"."city_translation" 
            FROM "places"."cities"
            INNER JOIN "localization"."local_cities" ON "localization"."local_cities".lang = $langId AND "places"."cities"."city_id" = "localization"."local_cities"."city_id"
        """)

        if (name != null) {
            sb.append(""" WHERE "localization"."local_cities"."city_translation" LIKE '%$name%' """)
        }

        coordinates?.asPgPoint()?.value?.let { stringCoords ->
            sb.append("""  ORDER BY "places"."cities"."coordinates" <-> '$stringCoords'  """)
        }

        if (limit > 0) {
            sb.append(" LIMIT ").append(limit)
        }

        return Database.rawSelect(sb.toString(), EntityComposer::buildCitiesFromResultSet)
    }

    override suspend fun getCityById(id: Long, localizationId: Long): City? {
        val langId = localizationId.coerceAtLeast(1)

        val query = """
            SELECT 
               "places"."cities"."city_id",
               "places"."cities"."country_id",
               "places"."cities"."coordinates",
               "localization"."local_cities"."city_translation" 
            FROM "places"."cities"
            INNER JOIN "localization"."local_cities" 
                ON "localization"."local_cities".lang = $langId AND "places"."cities"."city_id" = "localization"."local_cities"."city_id"
            WHERE "places"."cities"."city_id" = $id
            """

        val cities = Database.rawSelect(query, EntityComposer::buildCitiesFromResultSet)

        return cities.firstOrNull()
    }
}