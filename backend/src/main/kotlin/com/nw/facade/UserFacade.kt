package com.nw.facade

import com.nw.models.User

interface UserFacade {
    suspend fun getAllUsers(): List<User>
    suspend fun findUserById(id: Int): User?
    suspend fun findUserByUsername(username: String): User?
    suspend fun findUserByUsernameAndPassword(user: String, password: String): User?
    suspend fun addNewUser(user: User): User?
    suspend fun editUser(userId: Int, firstName: String, lastName: String): Boolean
    suspend fun deleteUser(id: Int): Boolean
    suspend fun checkIfUsernameExists(username: String): Boolean
    suspend fun verifyUser(userId: Int, password: String): Boolean
}
