package ru.pavelapk.weatherapp.domain.weather.model

import kotlinx.datetime.LocalDateTime

data class CurrentWeather(
    val time: LocalDateTime,
    val temp: Int,
    val weatherCode: Int,
    val isNight: Boolean,
    val windSpeed: Double,
    val windDirection: Double,
)
