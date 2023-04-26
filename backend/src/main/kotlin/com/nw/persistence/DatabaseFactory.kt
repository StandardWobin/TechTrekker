package com.nw.persistence

import com.nw.const.Constants
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val driverClassName = "com.mysql.cj.jdbc.Driver"
        val jdbcURL = Constants.jdbcURL
        val user = Constants.user
        val password = Constants.password
        val database = Database.connect(jdbcURL, driverClassName, user, password)
        transaction(database) {
            addLogger(StdOutSqlLogger)
            // SchemaUtils.create(Books, Users, Tags, BookTags, Favorites, UserBooks)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}
