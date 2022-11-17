package ru.pavelapk.weatherapp.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.pavelapk.weatherapp.domain.weather.ObserveCurrentWeatherUseCase
import ru.pavelapk.weatherapp.domain.weather.ObserveCurrentWeatherUseCaseImpl
import ru.pavelapk.weatherapp.domain.weather.RefreshWeatherUseCase
import ru.pavelapk.weatherapp.domain.weather.RefreshWeatherUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun getObserveCurrentWeatherUseCase(observeCurrentWeatherUseCaseImpl: ObserveCurrentWeatherUseCaseImpl): ObserveCurrentWeatherUseCase

    @Binds
    fun getRefreshWeatherUseCase(refreshWeatherUseCaseImpl: RefreshWeatherUseCaseImpl): RefreshWeatherUseCase

}
