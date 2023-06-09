package com.nw.utils

import com.nw.facade.FilterFacade
import com.nw.facade.JobPostingFacade
import com.nw.facade.JobResultFacade
import com.nw.facade.JobSiteFacade
import com.nw.facade.TechnologyFacade
import com.nw.facade.UserFacade
import com.nw.persistence.FilterRepository
import com.nw.persistence.JobPostingRepository
import com.nw.persistence.JobResultRepository
import com.nw.persistence.JobSiteRepository
import com.nw.persistence.TechnologyRepository
import com.nw.persistence.UserRepository

val userFacade: UserFacade = UserRepository()
val filterFacade: FilterFacade = FilterRepository()
val technologyFacade: TechnologyFacade = TechnologyRepository()
val jobPostingFacade: JobPostingFacade = JobPostingRepository()
val jobSiteFacade: JobSiteFacade = JobSiteRepository()
val jobResultFacade: JobResultFacade = JobResultRepository()
