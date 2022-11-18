package ru.pavelapk.weatherapp.presentation.weather.model

import androidx.annotation.StringRes
import ru.pavelapk.weatherapp.presentation.common.ui.Action

sealed class WeatherAction : Action {
    data class Error(@StringRes val messageId: Int) : WeatherAction()
    object OpenSearchScreen : WeatherAction()
    object RequestDeviceLocation : WeatherAction()
}
