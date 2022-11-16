package ru.pavelapk.weatherapp.domain.weather

import kotlinx.datetime.LocalDate
import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import ru.pavelapk.weatherapp.domain.weather.model.DayWeather

interface WeatherRemoteDataSource {
    suspend fun getCurrentWeather(
        lat: Double,
        long: Double,
        startDate: LocalDate,
        endDate: LocalDate
    ): CurrentWeather

    suspend fun getDailyWeather(
        lat: Double,
        long: Double,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<DayWeather>
}
