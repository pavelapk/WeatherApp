package ru.pavelapk.weatherapp.presentation.search.model

import androidx.annotation.StringRes
import ru.pavelapk.weatherapp.presentation.common.ui.Action

sealed class SearchAction : Action {
    data class Error(@StringRes val messageId: Int) : SearchAction()
    object OpenWeatherScreen : SearchAction()
}
