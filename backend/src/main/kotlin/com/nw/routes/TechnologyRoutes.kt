package com.nw.routes

import com.nw.facade.TechnologyFacade
import com.nw.models.Technology
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
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

fun Application.configureTechnology(technologyFacade: TechnologyFacade) {
    routing {
        authenticate {
            route("/api/v1/Technologies") {
                get {
                    val technologies = technologyFacade.getAllTechnologies()
                    call.respond(technologies)
                }

                get("{id}") {
                    val id = call.parameters.getOrFail<Int>("id").toInt()
                    val technology: Technology? = technologyFacade.findTechnologyById(id)
                    if (technology != null) {
                        call.respond(technology)
                    }
                }

                delete("{id}") {
                    val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest)
                    if (technologyFacade.findTechnologyById(id) != null) {
                        technologyFacade.deleteTechnology(id)
                        call.respondText("Technology $id successfully removed.", status = HttpStatusCode.Accepted)
                    } else {
                        call.respondText("Technology $id not found", status = HttpStatusCode.NotFound)
                    }
                }

                post("/add") {
                    val technology: Technology = call.receive()
                    val newTechnology = technologyFacade.addNewTechnology(technology)
                    call.respond(HttpStatusCode.Created, newTechnology!!)
                }

                put("{id}/edit") {
                }
            }
        }
    }
}
