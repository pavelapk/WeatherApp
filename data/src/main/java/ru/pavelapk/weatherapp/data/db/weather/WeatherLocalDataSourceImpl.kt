package ru.pavelapk.weatherapp.data.db.weather

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.pavelapk.weatherapp.data.db.weather.model.DayWeatherEntity
import ru.pavelapk.weatherapp.data.db.weather.model.HourWeatherEntity
import ru.pavelapk.weatherapp.data.db.weather.model.toDb
import ru.pavelapk.weatherapp.data.db.weather.model.toDomain
import ru.pavelapk.weatherapp.domain.weather.WeatherLocalDataSource
import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import ru.pavelapk.weatherapp.domain.weather.model.DayWeather
import ru.pavelapk.weatherapp.domain.weather.model.HourWeather
import javax.inject.Inject

class WeatherLocalDataSourceImpl @Inject constructor(
    private val currentWeatherDao: CurrentWeatherDao,
    private val dayWeatherDao: DayWeatherDao,
    private val hourWeatherDao: HourWeatherDao
) : WeatherLocalDataSource {
    override fun observeCurrentWeather(): Flow<CurrentWeather> =
        currentWeatherDao.observeCurrentWeather().map { it.toDomain() }

    override suspend fun updateCurrentWeather(currentWeather: CurrentWeather) {
        currentWeatherDao.updateCurrentWeather(currentWeather.toDb())
    }

    override fun observeDailyWeather(): Flow<List<DayWeather>> =
        dayWeatherDao.observeDailyWeather().map { it.map(DayWeatherEntity::toDomain) }

    override suspend fun updateDailyWeather(dailyWeather: List<DayWeather>) {
        dayWeatherDao.clearAndInsertDailyWeather(dailyWeather.map(DayWeather::toDb))
    }

    override fun observeHourlyWeather(): Flow<List<HourWeather>> =
        hourWeatherDao.observeHourlyWeather().map { it.map(HourWeatherEntity::toDomain) }

    override suspend fun updateHourlyWeather(hourlyWeather: List<HourWeather>) {
        hourWeatherDao.clearAndInsertHourlyWeather(hourlyWeather.map(HourWeather::toDb))
    }
}
