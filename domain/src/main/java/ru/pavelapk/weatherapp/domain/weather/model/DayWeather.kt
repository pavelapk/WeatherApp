package ru.pavelapk.weatherapp.domain.weather.model

import kotlinx.datetime.LocalDate

data class DayWeather(
    val date: LocalDate,
    val weatherCode: Int,
    val maxTemp: Int,
    val minTemp: Int
)
