package ru.pavelapk.weatherapp.domain.location.datasource

import ru.pavelapk.weatherapp.domain.location.model.Coordinates

interface DeviceLocationDataSource {
    suspend fun getDeviceLocation(): Coordinates
}
