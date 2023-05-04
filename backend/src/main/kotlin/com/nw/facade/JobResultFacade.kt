package com.nw.facade

import com.nw.models.JobResult

interface JobResultFacade {
    suspend fun getAllJobResults(): List<JobResult>
    suspend fun findJobResultById(id: Int): JobResult?
    suspend fun addNewJobResult(jobResult: JobResult): JobResult?
    suspend fun editJobResult(id: Int): Boolean
    suspend fun deleteJobResult(id: Int): Boolean
}
