package ru.pavelapk.weatherapp.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.pavelapk.weatherapp.data.net.weather.WeatherRemoteDataSourceImpl
import ru.pavelapk.weatherapp.domain.weather.WeatherRemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindWeatherRemoteDataSource(weatherRemoteDataSourceImpl: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource

}