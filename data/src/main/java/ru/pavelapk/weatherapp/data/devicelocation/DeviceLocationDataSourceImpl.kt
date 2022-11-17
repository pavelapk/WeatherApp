package ru.pavelapk.weatherapp.data.devicelocation

import android.content.Context
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await
import ru.pavelapk.weatherapp.domain.location.datasource.DeviceLocationDataSource
import ru.pavelapk.weatherapp.domain.common.failure.DeviceLocationFailure
import ru.pavelapk.weatherapp.domain.location.model.Coordinates
import javax.inject.Inject

class DeviceLocationDataSourceImpl @Inject constructor(
    context: Context
) : DeviceLocationDataSource {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    override suspend fun getDeviceLocation(): Coordinates {
        val location = try {
            fusedLocationClient.lastLocation.await() ?: throw DeviceLocationFailure()
        } catch (e: SecurityException) {
            throw DeviceLocationFailure()
        }
        return Coordinates(location.latitude, location.longitude)
    }
}
