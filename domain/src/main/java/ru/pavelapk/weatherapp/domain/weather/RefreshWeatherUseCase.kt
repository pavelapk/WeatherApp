package ru.pavelapk.weatherapp.domain.weather

import ru.pavelapk.weatherapp.domain.common.usecase.UseCase
import ru.pavelapk.weatherapp.domain.location.model.Location
import ru.pavelapk.weatherapp.domain.weather.datasource.WeatherLocalDataSource
import ru.pavelapk.weatherapp.domain.weather.datasource.WeatherRemoteDataSource
import javax.inject.Inject

interface RefreshWeatherUseCase : UseCase<Location, Unit>

class RefreshWeatherUseCaseImpl @Inject constructor(
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : RefreshWeatherUseCase {
    override suspend fun execute(param: Location): Result<Unit> {
        val (currentWeather, hourlyWeather, dailyWeather) = weatherRemoteDataSource.getWeather(
            param.coordinates
        )
        weatherLocalDataSource.updateCurrentWeather(currentWeather)
        weatherLocalDataSource.updateHourlyWeather(hourlyWeather)
        weatherLocalDataSource.updateDailyWeather(dailyWeather)

        return Result.success(Unit)
    }
}
