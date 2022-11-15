package ru.pavelapk.weatherapp.domain.weather.model

import kotlinx.datetime.LocalDateTime

data class CurrentWeather(
    val currentDay: DayWeather,
    val time: LocalDateTime,
    val temp: Int,
    val weatherCode: Int,
    val windSpeed: Double,
    val windDirection: Double
)

// TODO hourly weather