package ru.pavelapk.weatherapp.domain.weather.model

import kotlinx.datetime.LocalDateTime

data class HourWeather(
    val time: LocalDateTime,
    val weatherCode: Int,
    val temp: Int,
)
