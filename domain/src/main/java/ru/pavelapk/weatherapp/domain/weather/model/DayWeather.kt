package ru.pavelapk.weatherapp.domain.weather.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class DayWeather(
    val date: LocalDate,
    val weatherCode: Int,
    val maxTemp: Int,
    val minTemp: Int,
    val sunrise: LocalDateTime,
    val sunset: LocalDateTime
)
