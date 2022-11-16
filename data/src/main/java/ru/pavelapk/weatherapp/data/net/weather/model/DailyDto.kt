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
) {
    fun dayToDomain(i: Int) = DayWeather(
        time[i],
        weathercode[i],
        temperatureMax[i].roundToInt(),
        temperatureMin[i].roundToInt(),
    )

    fun toDomain() = time.indices.map { i ->
        DayWeather(
            time[i],
            weathercode[i],
            temperatureMax[i].roundToInt(),
            temperatureMin[i].roundToInt(),
        )
    }
}
