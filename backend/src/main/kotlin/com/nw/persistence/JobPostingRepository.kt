package com.nw.persistence

import com.nw.facade.JobPostingFacade
import com.nw.models.JobPosting
import com.nw.models.JobPostings
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class JobPostingRepository : JobPostingFacade {

    private fun resultRowToJobPosting(row: ResultRow) =
        JobPosting(
            id = row[JobPostings.id],
            title = row[JobPostings.title],
            companyName = row[JobPostings.companyName],
            location = row[JobPostings.location],
            description = row[JobPostings.description],
            salary = row[JobPostings.salary]
        )

    override suspend fun getAllJobPostings(): List<JobPosting> = DatabaseFactory.dbQuery {
        JobPostings.selectAll().map(::resultRowToJobPosting)
    }

    override suspend fun findJobPostingById(id: Int): JobPosting? = DatabaseFactory.dbQuery {
        JobPostings.select { JobPostings.id eq id }
            .map(::resultRowToJobPosting)
            .singleOrNull()
    }

    override suspend fun addNewJobPosting(jobPosting: JobPosting): JobPosting? {
        return transaction {
            JobPostings.insert {
                it[title] = jobPosting.title
                it[companyName] = jobPosting.companyName
                it[location] = jobPosting.location
                it[description] = jobPosting.description
                it[salary] = jobPosting.salary
            }.let {
                jobPosting.copy(id = it[JobPostings.id])
            }
        }
    }

    override suspend fun editJobPosting(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteJobPosting(id: Int): Boolean {
        return transaction {
            JobPostings.deleteWhere { JobPostings.id eq id } > 0
        }
    }
}
