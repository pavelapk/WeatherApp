package ru.pavelapk.weatherapp.domain.location.datasource

import ru.pavelapk.weatherapp.domain.location.model.Coordinates
import ru.pavelapk.weatherapp.domain.location.model.Location

interface LocationRemoteDataSource {
    suspend fun findCities(query: String): List<Location>
    suspend fun findLocationByCoordinates(coordinates: Coordinates): Location?
}
