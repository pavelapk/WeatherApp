package ru.pavelapk.weatherapp.domain.common.failure

class DeviceLocationFailure(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message ?: cause?.message, cause)