package ru.pavelapk.weatherapp.presentation.common.mappers

import androidx.annotation.StringRes
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.domain.common.failure.ApiFailure
import ru.pavelapk.weatherapp.domain.common.failure.DeviceLocationFailure
import ru.pavelapk.weatherapp.domain.common.failure.NoConnectionFailure

object ErrorMappers {
    @StringRes
    fun errorToStringRes(err: Throwable) = when (err) {
        is NoConnectionFailure -> R.string.no_connection_error
        is ApiFailure -> R.string.api_request_error
        is DeviceLocationFailure -> R.string.device_location_error
        else -> R.string.unknown_error
    }
}
