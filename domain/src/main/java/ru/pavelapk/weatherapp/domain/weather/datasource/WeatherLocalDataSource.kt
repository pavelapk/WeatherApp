package ru.pavelapk.weatherapp.domain.weather.datasource

import kotlinx.coroutines.flow.Flow
import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import ru.pavelapk.weatherapp.domain.weather.model.DayWeather
import ru.pavelapk.weatherapp.domain.weather.model.HourWeather

interface WeatherLocalDataSource {
    fun observeCurrentWeather(): Flow<CurrentWeather?>
    suspend fun updateCurrentWeather(currentWeather: CurrentWeather)

    fun observeDailyWeather(): Flow<List<DayWeather>>
    suspend fun updateDailyWeather(dailyWeather: List<DayWeather>)

    fun observeHourlyWeather(): Flow<List<HourWeather>>
    suspend fun updateHourlyWeather(hourlyWeather: List<HourWeather>)
}
