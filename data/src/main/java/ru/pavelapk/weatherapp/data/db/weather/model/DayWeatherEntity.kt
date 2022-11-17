package ru.pavelapk.weatherapp.data.db.weather.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import ru.pavelapk.weatherapp.domain.weather.model.DayWeather

@Entity(tableName = "day_weather")
data class DayWeatherEntity(
    @PrimaryKey val date: LocalDate,
    val weatherCode: Int,
    val maxTemp: Int,
    val minTemp: Int,
    val sunrise: LocalDateTime,
    val sunset: LocalDateTime
)

internal fun DayWeatherEntity.toDomain() = DayWeather(
    date = date,
    weatherCode = weatherCode,
    maxTemp = maxTemp,
    minTemp = minTemp,
    sunrise = sunrise,
    sunset = sunset,
)

internal fun DayWeather.toDb() = DayWeatherEntity(
    date = date,
    weatherCode = weatherCode,
    maxTemp = maxTemp,
    minTemp = minTemp,
    sunrise = sunrise,
    sunset = sunset,
)
