package ru.pavelapk.weatherapp.presentation.weather.model

import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import ru.pavelapk.weatherapp.domain.weather.model.DayWeather
import ru.pavelapk.weatherapp.domain.weather.model.HourWeather

data class TodayAndCurrentWeather(
    val today: DayWeather?,
    val current: CurrentWeather,
    val hourly: List<HourWeather>
)
