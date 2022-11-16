package ru.pavelapk.weatherapp.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.pavelapk.weatherapp.domain.weather.GetCurrentWeatherUseCase
import ru.pavelapk.weatherapp.domain.weather.GetCurrentWeatherUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun getCurrentWeatherUseCase(getCurrentWeatherUseCaseImpl: GetCurrentWeatherUseCaseImpl): GetCurrentWeatherUseCase

}
