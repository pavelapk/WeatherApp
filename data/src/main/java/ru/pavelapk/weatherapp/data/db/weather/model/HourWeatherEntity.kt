package ru.pavelapk.weatherapp.data.db.weather.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime
import ru.pavelapk.weatherapp.domain.weather.model.HourWeather

@Entity(tableName = "hour_weather")
data class HourWeatherEntity(
    @PrimaryKey val time: LocalDateTime,
    val weatherCode: Int,
    val isNight: Boolean,
    val temp: Int,
)

internal fun HourWeatherEntity.toDomain() = HourWeather(
    time = time,
    weatherCode = weatherCode,
    isNight = isNight,
    temp = temp,
)

internal fun HourWeather.toDb() = HourWeatherEntity(
    time = time,
    weatherCode = weatherCode,
    isNight = isNight,
    temp = temp,
)