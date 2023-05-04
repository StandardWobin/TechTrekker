package com.nw.models

import org.jetbrains.exposed.sql.Table
import java.math.BigDecimal

data class JobResult(
    val id: Int,
    val jobPostingId: Int,
    val jobSiteId: Int,
    val title: String,
    val companyName: String,
    val location: String,
    val description: String,
    val salary: BigDecimal
)

object JobResults : Table() {
    val id = integer("id").autoIncrement().uniqueIndex()
    val jobPostingId = integer("job_posting_id")
    val jobSiteId = integer("job_site_id")
    val title = varchar("title", 255)
    val companyName = varchar("company_name", 255)
    val location = varchar("location", 255)
    val description = text("description")
    val salary = decimal("salary", 10, 2)

    override val primaryKey = PrimaryKey(id)
}
