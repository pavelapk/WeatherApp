package ru.pavelapk.weatherapp.data.net.weather

import kotlinx.datetime.LocalDate
import ru.pavelapk.weatherapp.domain.weather.WeatherRemoteDataSource
import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import ru.pavelapk.weatherapp.domain.weather.model.DayWeather
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherService: WeatherService
) : WeatherRemoteDataSource {
    override suspend fun getCurrentWeather(
        lat: Double,
        long: Double,
        startDate: LocalDate,
        endDate: LocalDate
    ): CurrentWeather {
        val forecast = weatherService.getForecast(
            latitude = lat,
            longitude = long,
            hourly = CurrentWeatherHourlyParameters,
            daily = CurrentWeatherDailyParameters,
            currentWeather = true,
            temperatureUnit = TemperatureUnit,
            windspeedUnit = WindspeedUnit,
            timezone = Timezone,
            startDate = startDate.toString(),
            endDate = endDate.toString()
        )

        return forecast.toDomainCurrentWeather()
    }

    override suspend fun getDailyWeather(
        lat: Double,
        long: Double,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<DayWeather> {
        val forecast = weatherService.getForecast(
            latitude = lat,
            longitude = long,
            hourly = listOf(),
            daily = DailyForecastDailyParameters,
            currentWeather = false,
            temperatureUnit = TemperatureUnit,
            windspeedUnit = WindspeedUnit,
            timezone = Timezone,
            startDate = startDate.toString(),
            endDate = endDate.toString()
        )
        if (forecast.daily == null) throw IllegalStateException()
        return forecast.daily.toDomain()
    }

    private companion object {
        val CurrentWeatherHourlyParameters = listOf(
            "temperature_2m",
            "weathercode"
        )
        val CurrentWeatherDailyParameters = listOf(
            "weathercode",
            "temperature_2m_max",
            "temperature_2m_min",
            "sunrise",
            "sunset"
        )
        val DailyForecastDailyParameters = listOf(
            "weathercode",
            "temperature_2m_max",
            "temperature_2m_min"
        )

        const val TemperatureUnit = "celsius"
        const val WindspeedUnit = "ms"
        const val Timezone = "auto"
    }
}