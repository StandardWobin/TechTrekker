package com.nw.models

import org.jetbrains.exposed.sql.Table

data class JobSite(
    val id: Int,
    val name: String,
    val url: String,
    val username: String?,
    val password: String?
)

object JobSites : Table() {
    val id = integer("id").autoIncrement().uniqueIndex()
    val name = varchar("name", 255)
    val url = varchar("url", 255)
    val username = varchar("username", 255).nullable()
    val password = varchar("password", 255).nullable()

    override val primaryKey = PrimaryKey(id)
}
