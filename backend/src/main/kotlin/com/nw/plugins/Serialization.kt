package com.nw.plugins

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.nw.utils.OffsetDateTimeDeserializer
import com.nw.utils.OffsetDateTimeSerializer
import io.ktor.serialization.jackson.jackson
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.get
import java.time.OffsetDateTime

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
            configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            registerModule(
                KotlinModule.Builder()
                    .withReflectionCacheSize(512)
                    .build().apply {
                        addSerializer(OffsetDateTime::class.java, OffsetDateTimeSerializer())
                        addDeserializer(OffsetDateTime::class.java, OffsetDateTimeDeserializer())
                    }
            )
        }
    }
}
