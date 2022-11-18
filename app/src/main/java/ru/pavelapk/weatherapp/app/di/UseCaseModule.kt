package ru.pavelapk.weatherapp.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.pavelapk.weatherapp.domain.location.*
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

    @Binds
    fun getGetDeviceLocationUseCase(getDeviceLocationUseCaseImpl: GetDeviceLocationUseCaseImpl): GetDeviceLocationUseCase

    @Binds
    fun updateCurrentLocationUseCase(updateCurrentLocationUseCaseImpl: UpdateCurrentLocationUseCaseImpl): UpdateCurrentLocationUseCase

    @Binds
    fun getCurrentLocationUseCase(getCurrentLocationUseCaseImpl: GetCurrentLocationUseCaseImpl): GetCurrentLocationUseCase

    @Binds
    fun findCitiesUseCase(findCitiesUseCaseImpl: FindCitiesUseCaseImpl): FindCitiesUseCase
}
