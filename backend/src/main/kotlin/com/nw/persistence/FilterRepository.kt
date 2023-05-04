package com.nw.persistence

import com.nw.facade.FilterFacade
import com.nw.models.Filter
import com.nw.models.Filters
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class FilterRepository : FilterFacade {

    private fun resultRowToFilter(row: ResultRow) =
        Filter(
            id = row[Filters.id],
            technology = row[Filters.technology],
            title = row[Filters.title],
            location = row[Filters.location],
            salary = row[Filters.salary]
        )

    override suspend fun getAllFilters(): List<Filter> = DatabaseFactory.dbQuery {
        Filters.selectAll().map(::resultRowToFilter)
    }

    override suspend fun findFilterById(id: Int): Filter? = DatabaseFactory.dbQuery {
        Filters.select { Filters.id eq id }
            .map(::resultRowToFilter)
            .singleOrNull()
    }

    override suspend fun addNewFilter(filter: Filter): Filter? {
        return transaction {
            Filters.insert {
                it[technology] = filter.technology
                it[title] = filter.title
                it[location] = filter.location
                it[salary] = filter.salary
            }.let {
                filter.copy(id = it[Filters.id])
            }
        }
    }

    override suspend fun editFilter(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFilter(id: Int): Boolean {
        return transaction {
            Filters.deleteWhere { Filters.id eq id } > 0
        }
    }
}
