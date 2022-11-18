package ru.pavelapk.weatherapp.domain.location

import ru.pavelapk.weatherapp.domain.common.usecase.UseCaseWithoutParam
import ru.pavelapk.weatherapp.domain.location.datasource.DeviceLocationDataSource
import ru.pavelapk.weatherapp.domain.location.datasource.LocationRemoteDataSource
import ru.pavelapk.weatherapp.domain.location.model.Location
import javax.inject.Inject

interface GetDeviceLocationUseCase : UseCaseWithoutParam<Location>

class GetDeviceLocationUseCaseImpl @Inject constructor(
    private val deviceLocationDataSource: DeviceLocationDataSource,
    private val locationRemoteDataSource: LocationRemoteDataSource
) : GetDeviceLocationUseCase {
    override suspend fun execute(): Result<Location> {
        val coordinates = deviceLocationDataSource.getDeviceLocation()
        val location = locationRemoteDataSource.findLocationByCoordinates(coordinates)
            ?: Location("", "", coordinates)

        return Result.success(location)
    }
}
