package com.nw.models

import org.jetbrains.exposed.sql.Table
import java.math.BigDecimal

data class Filter(
    val id: Int,
    val technology: Int,
    val title: String?,
    val location: String?,
    val salary: BigDecimal?
)

object Filters : Table() {
    val id = integer("id").autoIncrement().uniqueIndex()
    val technology = integer("technology")
    val title = varchar("title", 255).nullable()
    val location = varchar("location", 255).nullable()
    val salary = decimal("salary", 10, 2).nullable()

    override val primaryKey = PrimaryKey(id)
}
