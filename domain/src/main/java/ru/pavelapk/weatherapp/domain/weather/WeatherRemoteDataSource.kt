package ru.pavelapk.weatherapp.domain.weather

import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import ru.pavelapk.weatherapp.domain.weather.model.DayWeather
import ru.pavelapk.weatherapp.domain.weather.model.HourWeather

interface WeatherRemoteDataSource {

    suspend fun getWeather(
        lat: Double,
        long: Double
    ): Triple<CurrentWeather, List<HourWeather>, List<DayWeather>>

}
