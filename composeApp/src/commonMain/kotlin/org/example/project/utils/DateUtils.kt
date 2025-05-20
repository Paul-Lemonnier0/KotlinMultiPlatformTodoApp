package org.example.project.utils
import kotlinx.datetime.LocalDateTime

// Expected Implementation of the conversion to a date string (formatted)
expect fun getTodoDateString(dateTime: LocalDateTime, locale: String = "en"): String