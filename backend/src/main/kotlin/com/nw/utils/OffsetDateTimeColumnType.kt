package com.nw.utils

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.vendors.currentDialect
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class OffsetDateTimeColumnType : ColumnType() {
    override fun sqlType(): String = currentDialect.dataTypeProvider.floatType()

    override fun valueToDB(value: Any?): Any? = when (value) {
        null -> null
        is OffsetDateTime -> value.toEpochSecond()
        else -> error("Unexpected value of type OffsetDateTime: $value of ${value::class.qualifiedName}")
    }

    override fun valueFromDB(value: Any): OffsetDateTime = when (value) {
        is OffsetDateTime -> value
        is Long -> value.toOffsetDateTime()
        is Number -> value.toLong().toOffsetDateTime()
        is String -> value.toLong().toOffsetDateTime()
        else -> error("Unexpected value of type Long: $value of ${value::class.qualifiedName}")
    }
}

fun Table.offsetDateTime(name: String): Column<OffsetDateTime> = registerColumn(name, OffsetDateTimeColumnType())

val DEFAULT_BERLIN_ZONE: ZoneId = ZoneId.of("Europe/Berlin")

fun Long.toBerlinOffsetDateTime(): OffsetDateTime {
    val instant = Instant.ofEpochSecond(this)
    return OffsetDateTime.ofInstant(instant, convertToZoneOffset(DEFAULT_BERLIN_ZONE, this))
}

private fun convertToZoneOffset(zoneId: ZoneId, epochSecond: Long): ZoneOffset {
    return zoneId.rules.getOffset(Instant.ofEpochSecond(epochSecond))
}
