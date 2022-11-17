package ru.pavelapk.weatherapp.presentation.weather.model

import ru.pavelapk.weatherapp.presentation.common.ui.State

data class WeatherScreenState(
    val isLoading: Boolean = true,
    val locationName: String = ""
) : State