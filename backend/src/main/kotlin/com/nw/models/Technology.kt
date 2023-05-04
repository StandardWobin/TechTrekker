package com.nw.models

import org.jetbrains.exposed.sql.Table

data class Technology(
    val id: Int,
    val name: String,
    val description: String,
    val skillLevel: String
)

object Technologies : Table() {
    val id = integer("id").autoIncrement().uniqueIndex()
    val name = varchar("name", 255)
    val description = text("description")
    val skillLevel = varchar("skill_level", 255)

    override val primaryKey = PrimaryKey(id)
}
