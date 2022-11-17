package ru.pavelapk.weatherapp.domain.location

import ru.pavelapk.weatherapp.domain.common.usecase.UseCase
import ru.pavelapk.weatherapp.domain.location.datasource.LocationLocalDataSource
import ru.pavelapk.weatherapp.domain.location.model.Location
import javax.inject.Inject

interface UpdateCurrentLocationUseCase : UseCase<Location, Unit>

class UpdateCurrentLocationUseCaseImpl @Inject constructor(
    private val locationLocalDataSource: LocationLocalDataSource
) : UpdateCurrentLocationUseCase {
    override suspend fun execute(param: Location): Result<Unit> {
        locationLocalDataSource.updateCurrentLocation(param)
        return Result.success(Unit)
    }
}
