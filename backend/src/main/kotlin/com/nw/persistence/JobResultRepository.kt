package com.nw.persistence

import com.nw.facade.JobResultFacade
import com.nw.models.JobResult
import com.nw.models.JobResults
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class JobResultRepository : JobResultFacade {

    private fun resultRowToJobResult(row: ResultRow) =
        JobResult(
            id = row[JobResults.id],
            jobPostingId = row[JobResults.jobPostingId],
            jobSiteId = row[JobResults.jobSiteId],
            title = row[JobResults.title],
            companyName = row[JobResults.companyName],
            location = row[JobResults.location],
            description = row[JobResults.description],
            salary = row[JobResults.salary]
        )

    override suspend fun getAllJobResults(): List<JobResult> = DatabaseFactory.dbQuery {
        JobResults.selectAll().map(::resultRowToJobResult)
    }

    override suspend fun findJobResultById(id: Int): JobResult? = DatabaseFactory.dbQuery {
        JobResults.select { JobResults.id eq id }
            .map(::resultRowToJobResult)
            .singleOrNull()
    }

    override suspend fun addNewJobResult(jobResult: JobResult): JobResult? {
        return transaction {
            JobResults.insert {
                it[jobPostingId] = jobResult.jobPostingId
                it[jobSiteId] = jobResult.jobSiteId
                it[title] = jobResult.title
                it[companyName] = jobResult.companyName
                it[location] = jobResult.location
                it[description] = jobResult.description
                it[salary] = jobResult.salary
            }.let {
                jobResult.copy(id = it[JobResults.id])
            }
        }
    }

    override suspend fun editJobResult(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteJobResult(id: Int): Boolean {
        return transaction {
            JobResults.deleteWhere { JobResults.id eq id } > 0
        }
    }
}
