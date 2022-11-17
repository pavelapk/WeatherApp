package ru.pavelapk.weatherapp.data.db.location

import ru.pavelapk.weatherapp.data.db.location.model.toDb
import ru.pavelapk.weatherapp.data.db.location.model.toDomain
import ru.pavelapk.weatherapp.domain.location.datasource.LocationLocalDataSource
import ru.pavelapk.weatherapp.domain.location.model.Location
import javax.inject.Inject

class LocationLocalDataSourceImpl @Inject constructor(
    private val locationDao: LocationDao
) : LocationLocalDataSource {
    override suspend fun getCurrentLocation(): Location? =
        locationDao.getCurrentLocation()?.toDomain()

    override suspend fun updateCurrentLocation(location: Location) {
        locationDao.updateCurrentLocation(location.toDb())
    }
}
