package ru.pavelapk.weatherapp.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import ru.pavelapk.weatherapp.domain.location.GetCurrentLocationUseCase
import ru.pavelapk.weatherapp.domain.weather.ObserveCurrentWeatherUseCase
import ru.pavelapk.weatherapp.domain.weather.ObserveDayWeatherUseCase
import ru.pavelapk.weatherapp.domain.weather.ObserveHourWeatherUseCase
import ru.pavelapk.weatherapp.domain.weather.RefreshWeatherUseCase
import ru.pavelapk.weatherapp.presentation.common.mappers.ErrorMappers.errorToStringRes
import ru.pavelapk.weatherapp.presentation.common.ui.StatefulViewModel
import ru.pavelapk.weatherapp.presentation.common.utils.DateTimeUtils.plus
import ru.pavelapk.weatherapp.presentation.weather.model.TodayAndCurrentWeather
import ru.pavelapk.weatherapp.presentation.weather.model.WeatherAction
import ru.pavelapk.weatherapp.presentation.weather.model.WeatherScreenState
import ru.pavelapk.weatherapp.presentation.weather.model.WeatherState
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val observeCurrentWeatherUseCase: ObserveCurrentWeatherUseCase,
    private val observeHourWeatherUseCase: ObserveHourWeatherUseCase,
    private val observeDayWeatherUseCase: ObserveDayWeatherUseCase,
    private val refreshWeatherUseCase: RefreshWeatherUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
) : StatefulViewModel<WeatherScreenState, WeatherAction>(defaultState = WeatherScreenState()) {

    init {
        refreshWeather()
    }

    val weatherState: LiveData<WeatherState> = initObserver().asLiveData()

    fun refreshWeather() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            getCurrentLocationUseCase().onSuccess { location ->
                if (location == null) {
                    postEvent(WeatherAction.RequestDeviceLocation)
                } else {
                    updateState { copy(locationName = location.name) }
                    refreshWeatherUseCase(location).onFailure {
                        postEvent(WeatherAction.Error(errorToStringRes(it)))
                    }
                }
            }
            updateState { copy(isLoading = false) }
        }
    }

    private fun initObserver() = combine(
        observeCurrentWeatherUseCase(),
        observeHourWeatherUseCase(),
        observeDayWeatherUseCase()
    ) { currentWeather, hourlyWeather, dailyWeather ->
        WeatherState(
            currentWeather = currentWeather?.let {
                val next24Hours =
                    currentWeather.time..currentWeather.time.plus(24, DateTimeUnit.HOUR)
                TodayAndCurrentWeather(
                    today = dailyWeather.firstOrNull { it.date == currentWeather.time.date },
                    current = currentWeather,
                    hourly = hourlyWeather.filter { it.time in next24Hours }
                )
            },
            dailyWeather = dailyWeather
        )
    }
}
