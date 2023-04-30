package com.nw.models

data class JobSite(
    val id: Int,
    val name: String,
    val url: String,
    val username: String?,
    val password: String?
)
