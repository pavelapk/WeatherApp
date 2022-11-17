package ru.pavelapk.weatherapp.data.db.location.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_location")
data class CurrentLocationEntity(
    @PrimaryKey val id: Int = 0,
    val lat: Double,
    val long: Double,
    val locationName: Int,
)