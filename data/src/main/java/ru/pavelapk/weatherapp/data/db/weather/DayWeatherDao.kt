package ru.pavelapk.weatherapp.data.db.weather

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.pavelapk.weatherapp.data.db.weather.model.DayWeatherEntity

@Dao
interface DayWeatherDao {
    @Query("SELECT * FROM day_weather")
    fun observeDailyWeather(): Flow<List<DayWeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyWeather(dailyWeather: List<DayWeatherEntity>)

    @Query("DELETE FROM day_weather")
    suspend fun clearDailyWeather()

    @Transaction
    suspend fun clearAndInsertDailyWeather(dailyWeather: List<DayWeatherEntity>) {
        clearDailyWeather()
        insertDailyWeather(dailyWeather)
    }
}
