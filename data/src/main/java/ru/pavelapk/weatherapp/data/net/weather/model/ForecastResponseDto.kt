package ru.pavelapk.weatherapp.data.net.weather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponseDto(
    @SerialName("current_weather")
    val currentWeather: CurrentWeatherDto,
    val hourly: HourlyDto,
    val daily: DailyDto
)