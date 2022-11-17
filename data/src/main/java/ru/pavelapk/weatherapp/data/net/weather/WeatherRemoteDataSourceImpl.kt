package ru.pavelapk.weatherapp.data.net.weather

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import ru.pavelapk.weatherapp.data.net.weather.model.toDomain
import ru.pavelapk.weatherapp.domain.location.model.Coordinates
import ru.pavelapk.weatherapp.domain.weather.datasource.WeatherRemoteDataSource
import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import ru.pavelapk.weatherapp.domain.weather.model.DayWeather
import ru.pavelapk.weatherapp.domain.weather.model.HourWeather
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherService: WeatherService
) : WeatherRemoteDataSource {

    override suspend fun getWeather(
        coordinates: Coordinates,
    ): Triple<CurrentWeather, List<HourWeather>, List<DayWeather>> {
        val forecast = weatherService.getForecast(
            latitude = coordinates.latitude,
            longitude = coordinates.longitude,
            hourly = HourlyParameters,
            daily = DailyParameters,
            currentWeather = true,
            temperatureUnit = TemperatureUnit,
            windspeedUnit = WindspeedUnit,
            timezone = Timezone,
        )

        val dailyWeather = forecast.daily.toDomain()

        val dayRangeMap = makeDayRangeMap(dailyWeather)

        return Triple(
            forecast.currentWeather.toDomain(dayRangeMap),
            forecast.hourly.toDomain(dayRangeMap),
            dailyWeather
        )
    }

    private fun makeDayRangeMap(days: List<DayWeather>): Map<LocalDate, ClosedRange<LocalDateTime>> {
        return days.associate { Pair(it.date, it.sunrise..it.sunset) }
    }

    private companion object {
        val HourlyParameters = listOf(
            "temperature_2m",
            "weathercode"
        )
        val DailyParameters = listOf(
            "weathercode",
            "temperature_2m_max",
            "temperature_2m_min",
            "sunrise",
            "sunset"
        )

        const val TemperatureUnit = "celsius"
        const val WindspeedUnit = "ms"
        const val Timezone = "auto"
    }
}