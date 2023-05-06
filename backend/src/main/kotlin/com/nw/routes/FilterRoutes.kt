package com.nw.routes

import com.nw.facade.FilterFacade
import com.nw.models.Filter
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

fun Application.configureFilter(filterFacade: FilterFacade) {
    routing {
        // authenticate {
        route("/api/v1/filters") {
            get {
                val filters = filterFacade.getAllFilters()
                call.respond(filters)
            }

            get("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val filter: Filter? = filterFacade.findFilterById(id)
                if (filter != null) {
                    call.respond(filter)
                }
            }

            delete("{id}") {
                val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest)
                if (filterFacade.findFilterById(id) != null) {
                    filterFacade.deleteFilter(id)
                    call.respondText("Filter $id successfully removed.", status = HttpStatusCode.Accepted)
                } else {
                    call.respondText("Filter $id not found", status = HttpStatusCode.NotFound)
                }
            }

            post("/add") {
                val filter: Filter = call.receive()
                val newFilter = filterFacade.addNewFilter(filter)
                call.respond(HttpStatusCode.Created, newFilter!!)
            }

            put("{id}/edit") {
            }
        }
    }
    // }
}
