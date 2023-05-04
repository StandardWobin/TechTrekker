package com.nw.models

import org.jetbrains.exposed.sql.Table
import java.math.BigDecimal

data class JobPosting(
    val id: Int,
    val title: String,
    val companyName: String,
    val location: String,
    val description: String,
    val salary: BigDecimal
)

object JobPostings : Table() {
    val id = integer("id").autoIncrement().uniqueIndex()
    val title = varchar("title", 255)
    val companyName = varchar("company_name", 255)
    val location = varchar("location", 255)
    val description = text("description")
    val salary = decimal("salary", 10, 2)

    override val primaryKey = PrimaryKey(id)
}
