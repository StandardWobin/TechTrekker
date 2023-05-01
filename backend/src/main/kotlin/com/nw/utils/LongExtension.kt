package com.nw.utils

import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/** Converts UNIX Timestamp (seconds) to a date with an offset date time ISO8601:
 *  like 2021-10-04T12:07:01+02:00 */
fun Long.toOffsetDateTimeString(): String {
    return this.toOffsetDateTime().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
}

fun Long.toOffsetDateTime(): OffsetDateTime {
    val instant = Instant.ofEpochSecond(this)
    return OffsetDateTime.ofInstant(instant, ZoneOffset.UTC)
}
