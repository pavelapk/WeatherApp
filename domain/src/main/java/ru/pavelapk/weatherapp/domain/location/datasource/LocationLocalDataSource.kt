package ru.pavelapk.weatherapp.domain.location.datasource

import ru.pavelapk.weatherapp.domain.location.model.Location

interface LocationLocalDataSource {
    suspend fun getCurrentLocation(): Location?
    suspend fun updateCurrentLocation(location: Location)
}
