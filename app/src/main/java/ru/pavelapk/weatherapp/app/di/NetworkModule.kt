package ru.pavelapk.weatherapp.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.pavelapk.weatherapp.app.di.qualifiers.GeoRetrofit
import ru.pavelapk.weatherapp.app.di.qualifiers.WeatherRetrofit
import ru.pavelapk.weatherapp.data.BuildConfig
import ru.pavelapk.weatherapp.data.net.Network
import ru.pavelapk.weatherapp.data.net.common.interceptor.ErrorInterceptor
import ru.pavelapk.weatherapp.data.net.location.LocationService
import ru.pavelapk.weatherapp.data.net.weather.WeatherService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideJson() = Network.appJson

    @OptIn(ExperimentalSerializationApi::class)
    @WeatherRetrofit
    @Singleton
    @Provides
    fun provideRetrofitWeather(client: OkHttpClient, json: Json) = Network.getRetrofit(
        client = client,
        url = BuildConfig.WEATHER_API_URL,
        json = json
    )

    @OptIn(ExperimentalSerializationApi::class)
    @GeoRetrofit
    @Singleton
    @Provides
    fun provideRetrofitGeo(client: OkHttpClient, json: Json) = Network.getRetrofit(
        client = client,
        url = BuildConfig.GEO_API_URL,
        json = json
    )

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient = Network.getHttpClient(
        interceptors = listOf(ErrorInterceptor()),
        isDebug = BuildConfig.DEBUG
    )

    @Singleton
    @Provides
    fun provideWeatherService(
        @WeatherRetrofit retrofit: Retrofit
    ) = Network.getApi<WeatherService>(retrofit)

    @Singleton
    @Provides
    fun provideLocationService(
        @GeoRetrofit retrofit: Retrofit
    ) = Network.getApi<LocationService>(retrofit)

}
