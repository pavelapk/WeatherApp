package ru.pavelapk.weatherapp.domain.weather

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.*
import ru.pavelapk.weatherapp.domain.common.usecase.FlowUseCaseWithoutParam
import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import javax.inject.Inject

interface GetWeatherUseCase : FlowUseCaseWithoutParam<CurrentWeather>

class GetWeatherUseCaseImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : GetWeatherUseCase {
    override fun execute(): Flow<Result<CurrentWeather>> = flow {
        val now = Clock.System.now()
        val today = now.toLocalDateTime(TimeZone.currentSystemDefault()).date
        val tomorrow = today.plus(DatePeriod(days = 1))

        var weather = weatherRemoteDataSource.getCurrentWeather(56.60, 84.88, today, tomorrow)

        val currentTime = weather.time
        weather = weather.copy(
            hourlyWeather = weather.hourlyWeather.filter { it.time >= currentTime }
        )

        emit(Result.success(weather))
    }
}
