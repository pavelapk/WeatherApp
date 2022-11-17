package ru.pavelapk.weatherapp.data.net.weather.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.pavelapk.weatherapp.domain.weather.model.HourWeather
import kotlin.math.roundToInt

@Serializable
data class HourlyDto(
    val time: List<LocalDateTime>,
    val weathercode: List<Int>,

    @SerialName("temperature_2m")
    val temperature: List<Double>
)

internal fun HourlyDto.toDomain(dayRange: Map<LocalDate, ClosedRange<LocalDateTime>>) =
    time.indices.map { i ->
        HourWeather(
            time = time[i],
            weatherCode = weathercode[i],
            isNight = dayRange[time[i].date]?.contains(time[i])?.not() ?: false,
            temp = temperature[i].roundToInt()
        )
    }

