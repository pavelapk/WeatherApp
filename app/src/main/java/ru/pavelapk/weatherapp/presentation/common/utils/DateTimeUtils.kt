package ru.pavelapk.weatherapp.presentation.common.utils

import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import java.time.format.DateTimeFormatter
import java.util.*

object DateTimeUtils {
    private val dateFormatter = DateTimeFormatter.ofPattern("EEEE, d MMMM")
    private val timeFormatter = DateTimeFormatter.ofPattern("d MMM HH:mm")

    fun formatDate(date: LocalDate): String =
        date.toJavaLocalDate().format(dateFormatter.withLocale(Locale.getDefault()))

    fun formatTime(time: LocalDateTime): String =
        time.toJavaLocalDateTime().format(timeFormatter.withLocale(Locale.getDefault()))

    fun LocalDateTime.plus(amount: Int, unit: DateTimeUnit.TimeBased): LocalDateTime {
        val instant = toInstant(TimeZone.UTC)
        val newInstant = instant.plus(amount, unit)
        return newInstant.toLocalDateTime(TimeZone.UTC)
    }
}