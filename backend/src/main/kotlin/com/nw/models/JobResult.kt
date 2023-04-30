package com.nw.models

import java.math.BigDecimal

data class JobResult(
    val id: Int,
    val jobPostingId: Int,
    val jobSiteId: Int,
    val title: String,
    val companyName: String,
    val location: String,
    val description: String,
    val salary: BigDecimal
)
