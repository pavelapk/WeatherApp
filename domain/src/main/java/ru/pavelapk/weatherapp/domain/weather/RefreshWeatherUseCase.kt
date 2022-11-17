package ru.pavelapk.weatherapp.domain.weather

import ru.pavelapk.weatherapp.domain.common.usecase.UseCaseWithoutParam
import javax.inject.Inject

interface RefreshWeatherUseCase : UseCaseWithoutParam<Unit>

class RefreshWeatherUseCaseImpl @Inject constructor(
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : RefreshWeatherUseCase {
    override suspend fun execute(): Result<Unit> {
        // TODO get location from db
        val (currentWeather, hourlyWeather, dailyWeather) = weatherRemoteDataSource.getWeather(
            56.60,
            84.88
        )
        weatherLocalDataSource.updateCurrentWeather(currentWeather)
        weatherLocalDataSource.updateHourlyWeather(hourlyWeather)
        weatherLocalDataSource.updateDailyWeather(dailyWeather)

        return Result.success(Unit)
    }

}