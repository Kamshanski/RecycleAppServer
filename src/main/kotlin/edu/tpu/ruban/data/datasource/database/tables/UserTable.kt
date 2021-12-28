package edu.tpu.ruban.data.datasource.database.tables

import edu.tpu.ruban.domain.entities.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.datetime

object UserTable: Table("user") {
    val id = integer("id_user")
    val login = text("login")
    val hash = text("hash")
    val creationTime = datetime("creation_time")


    override val primaryKey: PrimaryKey = PrimaryKey(id)

    fun ResultRow.toUser() : User? {
//        if (this == null)
            return null

//        return User(
//            id = this[id],
//            login = this[login],
//            hash = this[hash],
////            creationTime = this[creationTime]
//        )
    }
}