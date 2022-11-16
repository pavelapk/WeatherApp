package ru.pavelapk.weatherapp.data.net.weather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import kotlin.math.roundToInt

@Serializable
data class ForecastResponseDto(
    @SerialName("current_weather")
    val currentWeather: CurrentWeatherDto? = null,

    val hourly: HourlyDto? = null,
    val daily: DailyDto? = null
) {
    fun toDomainCurrentWeather(): CurrentWeather {
        if (daily == null || hourly == null || currentWeather == null) throw IllegalStateException()
        return CurrentWeather(
            currentDayWeather = daily.dayToDomain(0),
            time = currentWeather.time,
            temp = currentWeather.temperature.roundToInt(),
            weatherCode = currentWeather.weathercode,
            windSpeed = currentWeather.windspeed,
            windDirection = currentWeather.winddirection,
            hourlyWeather = hourly.toDomain(),
        )
    }
}