package com.nw.facade

import com.nw.models.Filter

interface FilterFacade {
    suspend fun getAllFilters(): List<Filter>
    suspend fun findFilterById(id: Int): Filter?
    suspend fun addNewFilter(filter: Filter): Filter?
    suspend fun editFilter(id: Int): Boolean
    suspend fun deleteFilter(id: Int): Boolean
}
