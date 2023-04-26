package com.nw.user.models

import java.time.LocalDateTime

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val createdAt: LocalDateTime
)
