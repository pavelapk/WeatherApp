package ru.pavelapk.weatherapp.domain.location

import ru.pavelapk.weatherapp.domain.common.usecase.UseCaseWithoutParam
import ru.pavelapk.weatherapp.domain.location.datasource.LocationLocalDataSource
import ru.pavelapk.weatherapp.domain.location.model.Location
import javax.inject.Inject

interface GetCurrentLocationUseCase : UseCaseWithoutParam<Location?>

class GetCurrentLocationUseCaseImpl @Inject constructor(
    private val locationLocalDataSource: LocationLocalDataSource
) : GetCurrentLocationUseCase {
    override suspend fun execute(): Result<Location?> {
        return Result.success(locationLocalDataSource.getCurrentLocation())
    }
}
