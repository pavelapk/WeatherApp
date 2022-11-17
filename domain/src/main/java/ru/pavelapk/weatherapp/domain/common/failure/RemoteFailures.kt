package ru.pavelapk.weatherapp.domain.common.failure

import java.io.IOException

sealed class CommonRemoteFailure(
    message: String? = null,
    cause: Throwable? = null
) : IOException(message ?: cause?.message, cause)

class NoConnectionFailure(
    message: String? = null,
    cause: Throwable? = null
) : CommonRemoteFailure(message, cause)

class FetchDataFailure( // Ошибка при загрузке данных
    message: String? = null,
    cause: Throwable? = null
) : CommonRemoteFailure(message, cause)