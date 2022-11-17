package ru.pavelapk.weatherapp.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.domain.common.failure.DeviceLocationFailure
import ru.pavelapk.weatherapp.domain.common.failure.FetchDataFailure
import ru.pavelapk.weatherapp.domain.common.failure.NoConnectionFailure
import ru.pavelapk.weatherapp.domain.location.GetCurrentLocationUseCase
import ru.pavelapk.weatherapp.domain.location.GetDeviceLocationUseCase
import ru.pavelapk.weatherapp.domain.location.UpdateCurrentLocationUseCase
import ru.pavelapk.weatherapp.domain.weather.ObserveCurrentWeatherUseCase
import ru.pavelapk.weatherapp.domain.weather.ObserveDayWeatherUseCase
import ru.pavelapk.weatherapp.domain.weather.ObserveHourWeatherUseCase
import ru.pavelapk.weatherapp.domain.weather.RefreshWeatherUseCase
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
    private val getDeviceLocationUseCase: GetDeviceLocationUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val updateCurrentLocationUseCase: UpdateCurrentLocationUseCase
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
                    postEvent(WeatherAction.RequestLocationPermission)
                    updateState { copy(isLoading = false) }
                } else {
                    updateState { copy(locationName = location.name) }
                    refreshWeatherUseCase(location).onFailure {
                        postEvent(
                            WeatherAction.Error(
                                when (it) {
                                    is NoConnectionFailure -> R.string.no_connection_error
                                    is FetchDataFailure -> R.string.fetch_data_error
                                    else -> R.string.unknown_error
                                }
                            )
                        )
                    }
                    updateState { copy(isLoading = false) }
                }
            }
        }
    }

    fun onRequestLocationPermissionResult(isGranted: Boolean) {
        if (isGranted) {
            viewModelScope.launch {
                getAndSaveDeviceLocation()
                refreshWeather()
            }
        } else {
            postEvent(WeatherAction.LocationPermissionNotGranted)
        }
    }

    private suspend fun getAndSaveDeviceLocation() {
        getDeviceLocationUseCase().onSuccess {
            updateState { copy(locationName = it.name) }
            updateCurrentLocationUseCase(it)
        }.onFailure {
            postEvent(
                WeatherAction.Error(
                    when (it) {
                        is DeviceLocationFailure -> R.string.device_location_error
                        else -> R.string.unknown_error
                    }
                )
            )
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
