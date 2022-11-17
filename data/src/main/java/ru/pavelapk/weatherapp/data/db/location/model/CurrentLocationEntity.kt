package ru.pavelapk.weatherapp.data.db.location.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.pavelapk.weatherapp.domain.location.model.Coordinates
import ru.pavelapk.weatherapp.domain.location.model.Location

@Entity(tableName = "current_location")
data class CurrentLocationEntity(
    @PrimaryKey val id: Int = 0,
    val lat: Double,
    val long: Double,
    val locationName: String,
)

internal fun CurrentLocationEntity.toDomain() = Location(
    name = locationName,
    coordinates = Coordinates(lat, long)
)

internal fun Location.toDb() = CurrentLocationEntity(
    lat = coordinates.latitude,
    long = coordinates.longitude,
    locationName = name
)
