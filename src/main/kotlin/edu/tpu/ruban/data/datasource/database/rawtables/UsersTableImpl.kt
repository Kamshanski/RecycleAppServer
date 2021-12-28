package edu.tpu.ruban.data.datasource.database.rawtables

import edu.tpu.ruban.data.api.tables.UsersTable
import edu.tpu.ruban.data.datasource.database.Database
import edu.tpu.ruban.data.datasource.database.EntityComposer
import edu.tpu.ruban.data.datasource.database.EntityComposer.toPgString
import edu.tpu.ruban.data.utils.appendWhere
import edu.tpu.ruban.domain.entities.Credentials
import edu.tpu.ruban.domain.entities.User
import edu.tpu.ruban.lib.security.Encryptor

/*
INSERT INTO "accounts"."user" (login, hash)
VALUES
(),
 */
class UsersTableImpl : UsersTable {
    override suspend fun getUsersMatching(login: String?, admin: Boolean?, banned: Boolean?, strictName: Boolean): List<User> {
        val queryBeginning = """
           SELECT * FROM "accounts"."user" 
        """

        val conditions = mutableListOf<String>()

        if (login != null) {
            conditions += """ "accounts"."user"."login" ${if (strictName) " = " else " LIKE "} ${login.toPgString()} """
        }

        if (admin != null) {
            conditions += """ admin = $admin """
        }

        if (banned != null) {
            conditions += """ ban = $banned """
        }

        val query = StringBuilder(queryBeginning).appendWhere(conditions).toString()

        return Database.rawSelect(query, EntityComposer::buildUsersFromResultSet)
    }

    override suspend fun createUser(credentials: Credentials) {
        val hash = Encryptor.passwordHash(credentials.password)

        val query = """
           INSERT INTO "accounts"."user" (login, hash) VALUES
           (${credentials.login.toPgString()}, ${hash.toPgString()})
        """

        Database.rawInsert(query)
    }
}