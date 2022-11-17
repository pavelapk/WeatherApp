package ru.pavelapk.weatherapp.domain.weather

import kotlinx.coroutines.flow.Flow
import ru.pavelapk.weatherapp.domain.common.usecase.FlowUseCase
import ru.pavelapk.weatherapp.domain.weather.model.HourWeather
import javax.inject.Inject

interface ObserveHourWeatherUseCase : FlowUseCase<List<HourWeather>>

class ObserveHourWeatherUseCaseImpl @Inject constructor(
    private val weatherLocalDataSource: WeatherLocalDataSource
) : ObserveHourWeatherUseCase {

    override fun execute(): Flow<List<HourWeather>> = weatherLocalDataSource.observeHourlyWeather()
}
