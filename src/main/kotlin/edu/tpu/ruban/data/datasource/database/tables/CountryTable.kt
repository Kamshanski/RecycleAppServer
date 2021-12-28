package edu.tpu.ruban.data.datasource.database.tables

import org.jetbrains.exposed.sql.Table

object CountryTable: Table("country") {
    val id = integer("id_country")
    val isoCode = varchar("iso_code", 3)

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}