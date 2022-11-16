package ru.pavelapk.weatherapp.presentation.weather.model

import ru.pavelapk.weatherapp.domain.weather.model.HourWeather

data class HourWeatherDayNight(
    val hourWeather: HourWeather,
    val isNight: Boolean
)
