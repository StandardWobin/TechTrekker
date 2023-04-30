package com.nw.models

import java.math.BigDecimal

data class Filter(
    val id: Int,
    val technology: Int,
    val title: String?,
    val location: String?,
    val salary: BigDecimal?
)
