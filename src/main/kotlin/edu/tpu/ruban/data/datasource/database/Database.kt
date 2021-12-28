package edu.tpu.ruban.data.datasource.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import edu.tpu.ruban.data.datasource.database.tables.CountryTable
import edu.tpu.ruban.data.datasource.database.tables.UserTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.ResultSet
import java.sql.SQLException

object Database {
    @Volatile
    var isInitialized: Boolean = false

    fun initialize() {
        var isInitializedAlready = isInitialized
        if (!isInitializedAlready) {
            synchronized(Database) {
                isInitializedAlready = isInitialized
                if (!isInitializedAlready) {
                    Database.connect(hikari())
                    transaction {
                        SchemaUtils.create(UserTable, CountryTable)
                    }
                }
            }
        }
    }

    fun hikari() : HikariDataSource {
        val config = HikariConfig().apply {
            driverClassName = System.getenv("JDBC_DRIVER")
            jdbcUrl = System.getenv("DATABASE_URL")
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        return HikariDataSource(config)
    }

    suspend fun <T> queryExpose(block: () -> T): T = withContext(Dispatchers.IO) {
        transaction { block() }
    }

    suspend fun <T: Any> rawSelect(sqlQuery: String, transform: (ResultSet) -> T) : T = queryExpose {
        TransactionManager.current().exec(sqlQuery, emptyList(), transform)
            ?: throw SQLException("Null Response")
    }

    suspend fun rawInsert(sqlQuery: String) : Unit = queryExpose {
        TransactionManager.current().exec(sqlQuery, emptyList())
    }

}