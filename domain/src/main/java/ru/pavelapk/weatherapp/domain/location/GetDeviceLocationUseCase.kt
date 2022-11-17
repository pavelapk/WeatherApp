package ru.pavelapk.weatherapp.domain.location

import ru.pavelapk.weatherapp.domain.common.usecase.UseCaseWithoutParam
import ru.pavelapk.weatherapp.domain.location.datasource.DeviceLocationDataSource
import ru.pavelapk.weatherapp.domain.location.model.Location
import javax.inject.Inject

interface GetDeviceLocationUseCase : UseCaseWithoutParam<Location>

class GetDeviceLocationUseCaseImpl @Inject constructor(
    private val deviceLocationDataSource: DeviceLocationDataSource
) : GetDeviceLocationUseCase {
    override suspend fun execute(): Result<Location> {
        val coordinates = deviceLocationDataSource.getDeviceLocation()
        // TODO reverse geocoding
        return Result.success(Location("Я тут", coordinates))
    }

}
