package com.nw.models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import java.time.LocalDateTime

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val createdAt: LocalDateTime
)

object Users : IntIdTable() {
    val username = varchar("username", 255).uniqueIndex()
    val password = varchar("password", 255)
    val email = varchar("email", 255).uniqueIndex()
    val firstName = varchar("first_name", 255)
    val lastName = varchar("last_name", 255)
    val createdAt = datetime("created_at")
}
