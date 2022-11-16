package ru.pavelapk.weatherapp.data.net

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object Network {
    private const val CONNECT_TIMEOUT = 15L
    private const val READ_TIMEOUT = 60L
    private const val WRITE_TIMEOUT = 30L

    val appJson: Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @ExperimentalSerializationApi
    fun getRetrofit(client: OkHttpClient, url: String, json: Json): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(url)
        .addConverterFactory(
            json.asConverterFactory(
                "application/json".toMediaType()
            )
        )
        .build()

    fun getHttpClient(
        isDebug: Boolean
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            if (isDebug) {
                val logLevel = HttpLoggingInterceptor.Level.BODY
                addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
            }
        }
        return httpClientBuilder.build()
    }

    inline fun <reified T> getApi(retrofit: Retrofit): T = retrofit.create(T::class.java)
}