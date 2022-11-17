package ru.pavelapk.weatherapp.data.net.weather.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import kotlin.math.roundToInt

@Serializable
data class CurrentWeatherDto(
    val temperature: Double,
    val windspeed: Double,
    val winddirection: Double,
    val weathercode: Int,
    val time: LocalDateTime
)

internal fun CurrentWeatherDto.toDomain(dayRange: Map<LocalDate, ClosedRange<LocalDateTime>>) =
    CurrentWeather(
        time = time,
        temp = temperature.roundToInt(),
        weatherCode = weathercode,
        isNight = dayRange[time.date]?.contains(time)?.not() ?: false,
        windSpeed = windspeed,
        windDirection = winddirection,
    )
