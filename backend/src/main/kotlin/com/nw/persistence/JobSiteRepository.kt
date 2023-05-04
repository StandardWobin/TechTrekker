package com.nw.persistence

import com.nw.facade.JobSiteFacade
import com.nw.models.JobSite
import com.nw.models.JobSites
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class JobSiteRepository : JobSiteFacade {

    private fun resultRowToJobSite(row: ResultRow) =
        JobSite(
            id = row[JobSites.id],
            name = row[JobSites.name],
            url = row[JobSites.url],
            username = row[JobSites.username],
            password = row[JobSites.password]
        )

    override suspend fun getAllJobSites(): List<JobSite> = DatabaseFactory.dbQuery {
        JobSites.selectAll().map(::resultRowToJobSite)
    }

    override suspend fun findJobSiteById(id: Int): JobSite? = DatabaseFactory.dbQuery {
        JobSites.select { JobSites.id eq id }
            .map(::resultRowToJobSite)
            .singleOrNull()
    }

    override suspend fun addNewJobSite(jobSite: JobSite): JobSite? {
        return transaction {
            JobSites.insert {
                it[name] = jobSite.name
                it[url] = jobSite.url
            }.let {
                jobSite.copy(id = it[JobSites.id])
            }
        }
    }

    override suspend fun editJobSite(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteJobSite(id: Int): Boolean {
        return transaction {
            JobSites.deleteWhere { JobSites.id eq id } > 0
        }
    }
}
