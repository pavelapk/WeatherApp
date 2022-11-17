package ru.pavelapk.weatherapp.data.db.location

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.pavelapk.weatherapp.data.db.location.model.CurrentLocationEntity

@Dao
interface LocationDao {

    @Query("SELECT * FROM current_location ORDER BY id DESC LIMIT 1")
    suspend fun getCurrentLocation(): CurrentLocationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCurrentLocation(currentLocationEntity: CurrentLocationEntity)

}