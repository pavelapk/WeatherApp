package ru.pavelapk.weatherapp.domain.weather.model

import kotlinx.datetime.LocalTime

data class HourWeather(
    val time: LocalTime,
    val weatherCode: Int,
    val temp: Int,
)
