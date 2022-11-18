package ru.pavelapk.weatherapp.presentation.main.model

import androidx.annotation.StringRes
import ru.pavelapk.weatherapp.presentation.common.ui.Action

sealed class MainAction : Action {
    data class Error(@StringRes val messageId: Int) : MainAction()
    object OpenWeatherScreen : MainAction()
}
