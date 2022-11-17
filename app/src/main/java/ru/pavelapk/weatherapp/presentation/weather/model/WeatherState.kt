package ru.pavelapk.weatherapp.presentation.weather.model

import ru.pavelapk.weatherapp.domain.weather.model.DayWeather
import ru.pavelapk.weatherapp.presentation.common.ui.State

data class WeatherState(
    val currentWeather: TodayAndCurrentWeather? = null,
    val dailyWeather: List<DayWeather> = listOf(),
) : State
