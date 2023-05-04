package com.nw.facade

import com.nw.models.JobPosting

interface JobPostingFacade {
    suspend fun getAllJobPostings(): List<JobPosting>
    suspend fun findJobPostingById(id: Int): JobPosting?
    suspend fun addNewJobPosting(jobPosting: JobPosting): JobPosting?
    suspend fun editJobPosting(id: Int): Boolean
    suspend fun deleteJobPosting(id: Int): Boolean
}
