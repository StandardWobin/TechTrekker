package com.nw

import com.nw.persistence.DatabaseFactory
import com.nw.plugins.configureAdministration
import com.nw.plugins.configureDatabases
import com.nw.plugins.configureHTTP
import com.nw.plugins.configureMonitoring
import com.nw.plugins.configureRouting
import com.nw.plugins.configureSecurity
import com.nw.plugins.configureSerialization
import com.nw.routes.configureFilter
import com.nw.routes.configureJobPosting
import com.nw.routes.configureTechnology
import com.nw.routes.configureUser
import com.nw.utils.filterFacade
import com.nw.utils.jobPostingFacade
import com.nw.utils.technologyFacade
import com.nw.utils.userFacade
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit =
    io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSerialization()
    DatabaseFactory.init()
    configureAdministration()
    configureDatabases()
    configureMonitoring()
    configureHTTP()
    configureSecurity()
    configureRouting()
    configureUser(userFacade)
    configureFilter(filterFacade)
    configureTechnology(technologyFacade)
    configureJobPosting(jobPostingFacade)
}
