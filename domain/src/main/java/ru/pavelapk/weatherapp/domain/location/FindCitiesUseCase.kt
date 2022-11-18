package ru.pavelapk.weatherapp.domain.location

import ru.pavelapk.weatherapp.domain.common.usecase.UseCase
import ru.pavelapk.weatherapp.domain.location.datasource.LocationRemoteDataSource
import ru.pavelapk.weatherapp.domain.location.model.Location
import javax.inject.Inject

interface FindCitiesUseCase : UseCase<String, List<Location>>

class FindCitiesUseCaseImpl @Inject constructor(
    private val locationRemoteDataSource: LocationRemoteDataSource
) : FindCitiesUseCase {
    override suspend fun execute(param: String): Result<List<Location>> =
        Result.success(locationRemoteDataSource.findCities(param))
}
