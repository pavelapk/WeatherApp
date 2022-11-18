package ru.pavelapk.weatherapp.data.net.common.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.pavelapk.weatherapp.domain.common.failure.ApiFailure
import ru.pavelapk.weatherapp.domain.common.failure.NoConnectionFailure
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val response = chain.proceed(chain.request())
            if (!response.isSuccessful) {
                throw ApiFailure()
            }
            return response
        } catch (e: UnknownHostException) {
            throw NoConnectionFailure()
        }
    }
}
