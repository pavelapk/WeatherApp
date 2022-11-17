package ru.pavelapk.weatherapp.data.db

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.pavelapk.weatherapp.data.db.common.Converters
import ru.pavelapk.weatherapp.data.db.location.LocationDao
import ru.pavelapk.weatherapp.data.db.location.model.CurrentLocationEntity
import ru.pavelapk.weatherapp.data.db.weather.CurrentWeatherDao
import ru.pavelapk.weatherapp.data.db.weather.DayWeatherDao
import ru.pavelapk.weatherapp.data.db.weather.HourWeatherDao
import ru.pavelapk.weatherapp.data.db.weather.model.CurrentWeatherEntity
import ru.pavelapk.weatherapp.data.db.weather.model.DayWeatherEntity
import ru.pavelapk.weatherapp.data.db.weather.model.HourWeatherEntity

@androidx.room.Database(
    entities = [
        CurrentLocationEntity::class,
        CurrentWeatherEntity::class,
        DayWeatherEntity::class,
        HourWeatherEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDayWeatherDao(): DayWeatherDao
    abstract fun getHourWeatherDao(): HourWeatherDao
    abstract fun getCurrentWeatherDao(): CurrentWeatherDao
    abstract fun getLocationDao(): LocationDao
}
