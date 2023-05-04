package com.nw.persistence

import com.jcraft.jsch.JSch
import com.nw.const.Constants.DB_HOST
import com.nw.const.Constants.DB_NAME
import com.nw.const.Constants.DB_PASSWORD
import com.nw.const.Constants.DB_PORT
import com.nw.const.Constants.DB_USER
import com.nw.const.Constants.DRIVER_CLASS_NAME
import com.nw.const.Constants.SSH_HOST
import com.nw.const.Constants.SSH_KEY_FILE
import com.nw.const.Constants.SSH_PORT
import com.nw.const.Constants.SSH_USER
import com.nw.models.Filters
import com.nw.models.JobPostings
import com.nw.models.JobResults
import com.nw.models.JobSites
import com.nw.models.Technologies
import com.nw.models.Users
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init() {
        val jsch = JSch()
        jsch.addIdentity(SSH_KEY_FILE)

        val session = jsch.getSession(SSH_USER, SSH_HOST, SSH_PORT)

        session.setConfig("StrictHostKeyChecking", "no")

        val localPort = session.setPortForwardingL(0, DB_HOST, DB_PORT)
        session.connect()

        val driverClassName = DRIVER_CLASS_NAME
        val jdbcURL = "jdbc:mysql://$DB_HOST:$localPort/$DB_NAME"
        val user = DB_USER
        val password = DB_PASSWORD
        val database = Database.connect(jdbcURL, driverClassName, user, password)
        transaction(database) {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Filters, JobPostings, JobResults, JobSites, Technologies, Users)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}
