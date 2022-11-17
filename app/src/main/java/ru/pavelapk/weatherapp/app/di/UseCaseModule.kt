package ru.pavelapk.weatherapp.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.pavelapk.weatherapp.domain.weather.*

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun getObserveCurrentWeatherUseCase(observeCurrentWeatherUseCaseImpl: ObserveCurrentWeatherUseCaseImpl): ObserveCurrentWeatherUseCase

    @Binds
    fun getObserveHourWeatherUseCase(observeHourWeatherUseCaseImpl: ObserveHourWeatherUseCaseImpl): ObserveHourWeatherUseCase

    @Binds
    fun getObserveDayWeatherUseCase(observeDayWeatherUseCaseImpl: ObserveDayWeatherUseCaseImpl): ObserveDayWeatherUseCase

    @Binds
    fun refreshWeatherUseCase(refreshWeatherUseCaseImpl: RefreshWeatherUseCaseImpl): RefreshWeatherUseCase

}
