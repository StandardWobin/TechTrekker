package com.nw.facade

import com.nw.models.Technology

interface TechnologyFacade {
    suspend fun getAllTechnologies(): List<Technology>
    suspend fun findTechnologyById(id: Int): Technology?
    suspend fun addNewTechnology(technology: Technology): Technology?
    suspend fun editTechnology(id: Int): Boolean
    suspend fun deleteTechnology(id: Int): Boolean
}
