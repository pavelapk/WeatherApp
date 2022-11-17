package ru.pavelapk.weatherapp.domain.weather

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.pavelapk.weatherapp.domain.common.usecase.FlowUseCaseWithoutParam
import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import javax.inject.Inject

interface ObserveCurrentWeatherUseCase : FlowUseCaseWithoutParam<CurrentWeather>

class ObserveCurrentWeatherUseCaseImpl @Inject constructor(
    private val weatherLocalDataSource: WeatherLocalDataSource
) : ObserveCurrentWeatherUseCase {

    override fun execute(): Flow<Result<CurrentWeather>> =
        weatherLocalDataSource.observeCurrentWeather().map { Result.success(it) }

}
