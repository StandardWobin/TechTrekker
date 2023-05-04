package com.nw.persistence

import com.nw.facade.TechnologyFacade
import com.nw.models.Technologies
import com.nw.models.Technology
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class TechnologyRepository : TechnologyFacade {

    private fun resultRowToTechnology(row: ResultRow) =
        Technology(
            id = row[Technologies.id],
            name = row[Technologies.name],
            description = row[Technologies.description],
            skillLevel = row[Technologies.skillLevel]
        )

    override suspend fun getAllTechnologies(): List<Technology> = DatabaseFactory.dbQuery {
        Technologies.selectAll().map(::resultRowToTechnology)
    }

    override suspend fun findTechnologyById(id: Int): Technology? = DatabaseFactory.dbQuery {
        Technologies.select { Technologies.id eq id }
            .map(::resultRowToTechnology)
            .singleOrNull()
    }

    override suspend fun addNewTechnology(technology: Technology): Technology? {
        return transaction {
            Technologies.insert {
                it[name] = technology.name
                it[description] = technology.description
                it[skillLevel] = technology.skillLevel
            }.let {
                technology.copy(id = it[Technologies.id])
            }
        }
    }

    override suspend fun editTechnology(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTechnology(id: Int): Boolean {
        return transaction {
            Technologies.deleteWhere { Technologies.id eq id } > 0
        }
    }
}
