package ru.pavelapk.weatherapp.domain.weather

import kotlinx.coroutines.flow.Flow
import ru.pavelapk.weatherapp.domain.common.usecase.FlowUseCase
import ru.pavelapk.weatherapp.domain.weather.model.DayWeather
import javax.inject.Inject

interface ObserveDayWeatherUseCase : FlowUseCase<List<DayWeather>>

class ObserveDayWeatherUseCaseImpl @Inject constructor(
    private val weatherLocalDataSource: WeatherLocalDataSource
) : ObserveDayWeatherUseCase {

    override fun execute(): Flow<List<DayWeather>> = weatherLocalDataSource.observeDailyWeather()
}
