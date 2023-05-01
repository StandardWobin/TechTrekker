package com.nw.routes

import com.nw.facade.UserFacade
import com.nw.models.User
import com.nw.models.UserEdit
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.util.getOrFail

fun Application.configureUser(userFacade: UserFacade) {
    routing {
        // authenticate {
        route("/api/v1/users") {
            get {
                val users = userFacade.getAllUsers()
                call.respond(users)
            }
            get("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val user: User? = userFacade.findUserById(id)
                if (user != null) {
                    call.respond(user)
                }
            }

            delete("{id}") {
                val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest)
                if (userFacade.findUserById(id) != null) {
                    userFacade.deleteUser(id)
                    call.respondText("User $id successfully removed.", status = HttpStatusCode.Accepted)
                } else {
                    call.respondText("User $id not found", status = HttpStatusCode.NotFound)
                }
            }

            post("/register") {
                val user: User = call.receive()
                if (userFacade.checkIfUsernameExists(user.username)) {
                    call.respond(HttpStatusCode.Conflict, "Username already taken.")
                } else {
                    val newUser = userFacade.addNewUser(user)
                    call.respond(HttpStatusCode.Created, newUser!!)
                }
            }

            put("/edit/{id}") {
                val id = call.parameters["id"]?.toInt() ?: return@put call.respond(HttpStatusCode.BadRequest)
                val editUser = call.receive<UserEdit>()

                val edited = userFacade.editUser(
                    id,
                    editUser.firstName,
                    editUser.lastName
                )

                if (edited) {
                    val updatedUser = userFacade.findUserById(id)
                    call.respond(HttpStatusCode.OK, updatedUser!!)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
        // }
    }
}
