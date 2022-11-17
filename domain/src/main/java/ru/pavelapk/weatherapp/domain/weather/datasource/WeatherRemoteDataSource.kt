package ru.pavelapk.weatherapp.domain.weather.datasource

import ru.pavelapk.weatherapp.domain.location.model.Coordinates
import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import ru.pavelapk.weatherapp.domain.weather.model.DayWeather
import ru.pavelapk.weatherapp.domain.weather.model.HourWeather

interface WeatherRemoteDataSource {

    suspend fun getWeather(coordinates: Coordinates): Triple<CurrentWeather, List<HourWeather>, List<DayWeather>>

}
