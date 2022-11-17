package ru.pavelapk.weatherapp.data.db.weather

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.pavelapk.weatherapp.data.db.weather.model.CurrentWeatherEntity

@Dao
interface CurrentWeatherDao {
    @Query("SELECT * FROM current_weather ORDER BY id DESC LIMIT 1")
    fun observeCurrentWeather(): Flow<CurrentWeatherEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCurrentWeather(currentWeatherEntity: CurrentWeatherEntity)
}
