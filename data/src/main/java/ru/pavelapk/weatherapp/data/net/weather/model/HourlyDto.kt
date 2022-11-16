package ru.pavelapk.weatherapp.data.net.weather.model

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
) {
    fun toDomain() = time.indices.map { i ->
        HourWeather(
            time[i],
            weathercode[i],
            temperature[i].roundToInt()
        )
    }
}
