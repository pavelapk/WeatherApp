package ru.pavelapk.weatherapp.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.pavelapk.weatherapp.domain.weather.GetWeatherUseCase
import ru.pavelapk.weatherapp.domain.weather.GetWeatherUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun getWeatherUseCase(getWeatherUseCaseImpl: GetWeatherUseCaseImpl): GetWeatherUseCase

}
