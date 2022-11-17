package ru.pavelapk.weatherapp.data.db.weather

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.pavelapk.weatherapp.data.db.weather.model.HourWeatherEntity

@Dao
interface HourWeatherDao {
    @Query("SELECT * FROM hour_weather")
    fun observeHourlyWeather(): Flow<List<HourWeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHourlyWeather(hourlyWeather: List<HourWeatherEntity>)

    @Query("DELETE FROM hour_weather")
    suspend fun clearHourlyWeather()

    @Transaction
    suspend fun clearAndInsertHourlyWeather(hourlyWeather: List<HourWeatherEntity>) {
        clearHourlyWeather()
        insertHourlyWeather(hourlyWeather)
    }
}
