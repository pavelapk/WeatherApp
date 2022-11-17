package ru.pavelapk.weatherapp.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import ru.pavelapk.weatherapp.domain.weather.ObserveCurrentWeatherUseCase
import ru.pavelapk.weatherapp.domain.weather.RefreshWeatherUseCase
import ru.pavelapk.weatherapp.domain.weather.model.DayWeather
import ru.pavelapk.weatherapp.presentation.common.ui.Action
import ru.pavelapk.weatherapp.presentation.common.ui.AppViewModel
import ru.pavelapk.weatherapp.presentation.weather.model.TodayAndCurrentWeather
import ru.pavelapk.weatherapp.presentation.weather.model.WeatherState
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val observeCurrentWeatherUseCase: ObserveCurrentWeatherUseCase,
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

    private fun initObserver() = flow {
        emit(WeatherState())

        observeCurrentWeatherUseCase().collect { result ->
            result.onSuccess {
                emit(
                    WeatherState(
                        isLoading = false,
                        currentWeather = TodayAndCurrentWeather(
                            today = DayWeather(
                                LocalDate(2022, 11, 15),
                                0,
                                5,
                                -5,
                                LocalDateTime(1, 1, 1, 0, 0),
                                LocalDateTime(1, 1, 1, 0, 0)
                            ),
                            current = it,
                            hourly = listOf()
                        ),
                    )
                )
            }.onFailure {
                emit(WeatherState(isLoading = false))
            }
        }
    }


}
