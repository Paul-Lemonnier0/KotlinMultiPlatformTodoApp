package org.example.project.utils

import kotlinx.datetime.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.LocalDateTime as JvmLocalDateTime
import java.util.Locale

actual fun getTodoDateString(
    dateTime: LocalDateTime,
    locale: String
): String {
    val formatter = DateTimeFormatter.ofPattern("EEE, MMM d, yyyy", Locale.forLanguageTag(locale))
    val jvmDateTime = JvmLocalDateTime.of(
        dateTime.year, dateTime.monthNumber, dateTime.dayOfMonth,
        dateTime.hour, dateTime.minute, dateTime.second
    )
    return formatter.format(jvmDateTime)
}