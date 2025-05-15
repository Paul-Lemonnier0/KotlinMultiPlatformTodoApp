package org.example.project.utils
import kotlinx.datetime.LocalDateTime

expect fun getTodoDateString(dateTime: LocalDateTime, locale: String = "en"): String