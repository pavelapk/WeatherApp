package ru.pavelapk.weatherapp.data.net.location.model

import kotlinx.serialization.Serializable
import ru.pavelapk.weatherapp.domain.location.model.Coordinates
import ru.pavelapk.weatherapp.domain.location.model.Location

@Serializable
data class PopulatedPlaceSummary(
    val name: String,
    val region: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
)

internal fun PopulatedPlaceSummary.toDomain() = Location(
    name = name,
    regionName = "$region, $country",
    coordinates = Coordinates(latitude, longitude)
)