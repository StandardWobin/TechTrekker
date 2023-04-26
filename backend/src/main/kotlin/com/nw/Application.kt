package com.nw

import com.nw.plugins.configureAdministration
import com.nw.plugins.configureDatabases
import com.nw.plugins.configureHTTP
import com.nw.plugins.configureMonitoring
import com.nw.plugins.configureRouting
import com.nw.plugins.configureSecurity
import com.nw.plugins.configureSerialization
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit =
    io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSerialization()
    configureAdministration()
    configureDatabases()
    configureMonitoring()
    configureHTTP()
    configureSecurity()
    configureRouting()
}
