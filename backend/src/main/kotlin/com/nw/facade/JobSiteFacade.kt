package com.nw.facade

import com.nw.models.JobSite

interface JobSiteFacade {
    suspend fun getAllJobSites(): List<JobSite>
    suspend fun findJobSiteById(id: Int): JobSite?
    suspend fun addNewJobSite(jobSite: JobSite): JobSite?
    suspend fun editJobSite(id: Int): Boolean
    suspend fun deleteJobSite(id: Int): Boolean
}
