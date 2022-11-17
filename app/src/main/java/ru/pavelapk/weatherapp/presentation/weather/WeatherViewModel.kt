package ru.pavelapk.weatherapp.presentation.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import ru.pavelapk.weatherapp.domain.weather.ObserveCurrentWeatherUseCase
import ru.pavelapk.weatherapp.domain.weather.ObserveDayWeatherUseCase
import ru.pavelapk.weatherapp.domain.weather.ObserveHourWeatherUseCase
import ru.pavelapk.weatherapp.domain.weather.RefreshWeatherUseCase
import ru.pavelapk.weatherapp.presentation.common.ui.Action
import ru.pavelapk.weatherapp.presentation.common.ui.AppViewModel
import ru.pavelapk.weatherapp.presentation.common.utils.DateTimeUtils.plus
import ru.pavelapk.weatherapp.presentation.weather.model.TodayAndCurrentWeather
import ru.pavelapk.weatherapp.presentation.weather.model.WeatherState
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val observeCurrentWeatherUseCase: ObserveCurrentWeatherUseCase,
    private val observeHourWeatherUseCase: ObserveHourWeatherUseCase,
    private val observeDayWeatherUseCase: ObserveDayWeatherUseCase,
    private val refreshWeatherUseCase: RefreshWeatherUseCase
) : AppViewModel<Action>() {

    init {
        refreshWeather()
    }

    val state: LiveData<WeatherState> = initObserver().asLiveData()

    fun refreshWeather() {
        viewModelScope.launch {
            refreshWeatherUseCase()
        }
    }

    private fun initObserver() = combine(
        observeCurrentWeatherUseCase(),
        observeHourWeatherUseCase(),
        observeDayWeatherUseCase()
    ) { currentWeather, hourlyWeather, dailyWeather ->
        Log.d("WeatherViewModel", "i am combine")
        val next24Hours = currentWeather.time..currentWeather.time.plus(24, DateTimeUnit.HOUR)

        WeatherState(
            isLoading = false,
            currentWeather = TodayAndCurrentWeather(
                today = dailyWeather.first { it.date == currentWeather.time.date },
                current = currentWeather,
                hourly = hourlyWeather.filter { it.time in next24Hours }
            ),
            dailyWeather = dailyWeather
        )
    }.onStart {
        emit(WeatherState())
    }


}
