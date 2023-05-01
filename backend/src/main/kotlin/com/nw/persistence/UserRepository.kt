package com.nw.persistence

import com.nw.facade.UserFacade
import com.nw.models.User
import com.nw.models.Users
import com.nw.security.hash
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class UserRepository : UserFacade {

    private fun resultRowToUser(row: ResultRow) =
        User(
            id = row[Users.id],
            username = row[Users.username],
            password = row[Users.password],
            email = row[Users.email],
            firstName = row[Users.firstName],
            lastName = row[Users.lastName],
            createdAt = row[Users.createdAt]
        )

    override suspend fun getAllUsers(): List<User> = DatabaseFactory.dbQuery {
        Users.selectAll().map(::resultRowToUser)
    }

    override suspend fun findUserById(id: Int): User? = DatabaseFactory.dbQuery {
        Users.select { Users.id eq id }
            .map(::resultRowToUser)
            .singleOrNull()
    }

    override suspend fun findUserByUsername(username: String): User? {
        return DatabaseFactory.dbQuery {
            Users.select { Users.username eq username }
                .map(::resultRowToUser)
                .singleOrNull()
        }
    }

    override suspend fun findUserByUsernameAndPassword(user: String, password: String): User? =
        DatabaseFactory.dbQuery {
            Users.select { Users.username eq user }
                .andWhere { Users.password eq password }
                .map(::resultRowToUser)
                .singleOrNull()
        }

    override suspend fun addNewUser(user: User): User? {
        return transaction {
            Users.insert {
                it[username] = user.username
                it[password] = hash(password = user.password)
                it[email] = user.email
                it[firstName] = user.firstName
                it[lastName] = user.lastName
                it[createdAt] = user.createdAt
            }.let {
                user.copy(id = it[Users.id])
            }
        }
    }

    override suspend fun editUser(userId: Int, firstName: String, lastName: String): Boolean = DatabaseFactory.dbQuery {
        Users.update({ Users.id eq userId }) {
            it[Users.firstName] = firstName
            it[Users.lastName] = lastName
        } > 0
    }

    override suspend fun deleteUser(id: Int): Boolean {
        return transaction {
            Users.deleteWhere { Users.id eq id } > 0
        }
    }

    override suspend fun checkIfUsernameExists(username: String): Boolean {
        return DatabaseFactory.dbQuery {
            val count = Users.select { Users.username eq username }
                .count()

            count > 0
        }
    }

    override suspend fun verifyUser(userId: Int, password: String): Boolean = DatabaseFactory.dbQuery {
        Users.select { Users.id eq userId }
            .andWhere { Users.password eq hash(password) }
            .singleOrNull() != null
    }
}
