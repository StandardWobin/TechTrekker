package com.nw.routes

import com.nw.facade.JobResultFacade
import com.nw.models.JobResult
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

fun Application.configureJobResult(jobResultFacade: JobResultFacade) {
    routing {
        authenticate {
            route("/api/v1/JobResults") {
                get {
                    val jobResults = jobResultFacade.getAllJobResults()
                    call.respond(jobResults)
                }

                get("{id}") {
                    val id = call.parameters.getOrFail<Int>("id").toInt()
                    val jobResult: JobResult? = jobResultFacade.findJobResultById(id)
                    if (jobResult != null) {
                        call.respond(jobResult)
                    }
                }

                delete("{id}") {
                    val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest)
                    if (jobResultFacade.findJobResultById(id) != null) {
                        jobResultFacade.deleteJobResult(id)
                        call.respondText("JobResult $id successfully removed.", status = HttpStatusCode.Accepted)
                    } else {
                        call.respondText("JobResult $id not found", status = HttpStatusCode.NotFound)
                    }
                }

                post("/add") {
                    val jobResult: JobResult = call.receive()
                    val newJobResult = jobResultFacade.addNewJobResult(jobResult)
                    call.respond(HttpStatusCode.Created, newJobResult!!)
                }

                put("{id}/edit") {
                }
            }
        }
    }
}
