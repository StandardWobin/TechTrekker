package com.nw.models

import java.math.BigDecimal

data class JobPosting(
    val id: Int,
    val title: String,
    val companyName: String,
    val location: String,
    val description: String,
    val salary: BigDecimal
)
