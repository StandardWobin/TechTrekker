package com.nw.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import kotlinx.serialization.SerializationException
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class OffsetDateTimeSerializer : StdSerializer<OffsetDateTime>(OffsetDateTime::class.java) {

    override fun serialize(value: OffsetDateTime?, gen: JsonGenerator?, provider: SerializerProvider?) {
        if (value == null) {
            gen?.writeNull()
        } else {
            gen?.writeString(value.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
        }
    }
}

class OffsetDateTimeDeserializer : StdDeserializer<OffsetDateTime>(OffsetDateTime::class.java) {

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): OffsetDateTime {
        val value = p?.valueAsString
        return if (value == null) {
            throw SerializationException("Cannot deserialize OffsetDateTime from null value")
        } else {
            OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        }
    }
}
