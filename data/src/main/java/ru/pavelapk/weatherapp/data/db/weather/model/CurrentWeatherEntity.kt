package ru.pavelapk.weatherapp.data.db.weather.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime
import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather

@Entity(tableName = "current_weather")
data class CurrentWeatherEntity(
    @PrimaryKey val id: Int = 0,
    val time: LocalDateTime,
    val temp: Int,
    val weatherCode: Int,
    val isNight: Boolean,
    val windSpeed: Double,
    val windDirection: Double,
)

internal fun CurrentWeatherEntity.toDomain() = CurrentWeather(
    time = time,
    temp = temp,
    weatherCode = weatherCode,
    isNight = isNight,
    windSpeed = windSpeed,
    windDirection = windDirection
)

internal fun CurrentWeather.toDb() = CurrentWeatherEntity(
    time = time,
    temp = temp,
    weatherCode = weatherCode,
    isNight = isNight,
    windSpeed = windSpeed,
    windDirection = windDirection
)
