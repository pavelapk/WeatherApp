package ru.pavelapk.weatherapp.data.net.weather.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.pavelapk.weatherapp.domain.weather.model.DayWeather
import kotlin.math.roundToInt

@Serializable
data class DailyDto(
    val time: List<LocalDate>,
    val weathercode: List<Int>,

    @SerialName("temperature_2m_max")
    val temperatureMax: List<Double>,

    @SerialName("temperature_2m_min")
    val temperatureMin: List<Double>,

    val sunrise: List<LocalDateTime>,
    val sunset: List<LocalDateTime>
)

internal fun DailyDto.dayToDomain(i: Int) = DayWeather(
    date = time[i],
    weatherCode = weathercode[i],
    maxTemp = temperatureMax[i].roundToInt(),
    minTemp = temperatureMin[i].roundToInt(),
    sunrise = sunrise[i],
    sunset = sunset[i]
)

internal fun DailyDto.toDomain() = time.indices.map { i ->
    dayToDomain(i)
}

