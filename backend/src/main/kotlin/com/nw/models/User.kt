package com.nw.models

import com.nw.utils.offsetDateTime
import org.jetbrains.exposed.sql.Table
import java.time.OffsetDateTime

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val createdAt: OffsetDateTime
)

object Users : Table() {
    val id = integer("id").autoIncrement().uniqueIndex()
    val username = varchar("username", 255).uniqueIndex()
    val password = varchar("password", 255)
    val email = varchar("email", 255).uniqueIndex()
    val firstName = varchar("first_name", 255)
    val lastName = varchar("last_name", 255)
    val createdAt = offsetDateTime("created_at")

    override val primaryKey = PrimaryKey(id)
}
