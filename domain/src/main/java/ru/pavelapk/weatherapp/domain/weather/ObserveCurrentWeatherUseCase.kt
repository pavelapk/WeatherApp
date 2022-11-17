package ru.pavelapk.weatherapp.domain.weather

import kotlinx.coroutines.flow.Flow
import ru.pavelapk.weatherapp.domain.common.usecase.FlowUseCase
import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import javax.inject.Inject

interface ObserveCurrentWeatherUseCase : FlowUseCase<CurrentWeather>

class ObserveCurrentWeatherUseCaseImpl @Inject constructor(
    private val weatherLocalDataSource: WeatherLocalDataSource
) : ObserveCurrentWeatherUseCase {

    override fun execute(): Flow<CurrentWeather> = weatherLocalDataSource.observeCurrentWeather()
}
