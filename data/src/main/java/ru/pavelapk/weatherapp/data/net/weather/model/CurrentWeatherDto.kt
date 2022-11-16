package ru.pavelapk.weatherapp.data.net.weather.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    val temperature: Double,
    val windspeed: Double,
    val winddirection: Double,
    val weathercode: Int,
    val time: LocalDateTime
)
