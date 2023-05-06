package com.nw.routes

import com.nw.facade.JobPostingFacade
import com.nw.models.JobPosting
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

fun Application.configureJobPosting(jobPostingFacade: JobPostingFacade) {
    routing {
        //  authenticate {
        route("/api/v1/JobPostings") {
            get {
                val jobPostings = jobPostingFacade.getAllJobPostings()
                call.respond(jobPostings)
            }

            get("{id}") {
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val jobPosting: JobPosting? = jobPostingFacade.findJobPostingById(id)
                if (jobPosting != null) {
                    call.respond(jobPosting)
                }
            }

            delete("{id}") {
                val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest)
                if (jobPostingFacade.findJobPostingById(id) != null) {
                    jobPostingFacade.deleteJobPosting(id)
                    call.respondText("JobPosting $id successfully removed.", status = HttpStatusCode.Accepted)
                } else {
                    call.respondText("JobPosting $id not found", status = HttpStatusCode.NotFound)
                }
            }

            post("/add") {
                val jobPosting: JobPosting = call.receive()
                val newJobPosting = jobPostingFacade.addNewJobPosting(jobPosting)
                call.respond(HttpStatusCode.Created, newJobPosting!!)
            }

            put("{id}/edit") {
            }
        }
    }
    // }
}
