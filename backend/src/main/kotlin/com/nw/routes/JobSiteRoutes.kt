package com.nw.routes

import com.nw.facade.JobSiteFacade
import com.nw.models.JobSite
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

fun Application.configureJobSite(jobSiteFacade: JobSiteFacade) {
    routing {
        authenticate {
            route("/api/v1/jobSites") {
                get {
                    val jobSites = jobSiteFacade.getAllJobSites()
                    call.respond(jobSites)
                }

                get("{id}") {
                    val id = call.parameters.getOrFail<Int>("id").toInt()
                    val jobSite: JobSite? = jobSiteFacade.findJobSiteById(id)
                    if (jobSite != null) {
                        call.respond(jobSite)
                    }
                }

                delete("{id}") {
                    val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest)
                    if (jobSiteFacade.findJobSiteById(id) != null) {
                        jobSiteFacade.deleteJobSite(id)
                        call.respondText("JobSite $id successfully removed.", status = HttpStatusCode.Accepted)
                    } else {
                        call.respondText("JobSite $id not found", status = HttpStatusCode.NotFound)
                    }
                }

                post("/add") {
                    val jobSite: JobSite = call.receive()
                    val newJobSite = jobSiteFacade.addNewJobSite(jobSite)
                    call.respond(HttpStatusCode.Created, newJobSite!!)
                }

                put("{id}/edit") {
                }
            }
        }
    }
}
