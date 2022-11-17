package ru.pavelapk.weatherapp.app.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.pavelapk.weatherapp.data.db.location.LocationLocalDataSourceImpl
import ru.pavelapk.weatherapp.data.db.weather.WeatherLocalDataSourceImpl
import ru.pavelapk.weatherapp.data.devicelocation.DeviceLocationDataSourceImpl
import ru.pavelapk.weatherapp.data.net.weather.WeatherRemoteDataSourceImpl
import ru.pavelapk.weatherapp.domain.location.datasource.DeviceLocationDataSource
import ru.pavelapk.weatherapp.domain.location.datasource.LocationLocalDataSource
import ru.pavelapk.weatherapp.domain.weather.datasource.WeatherLocalDataSource
import ru.pavelapk.weatherapp.domain.weather.datasource.WeatherRemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceBindsModule {

    @Binds
    fun bindWeatherRemoteDataSource(weatherRemoteDataSourceImpl: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource

    @Binds
    fun bindWeatherLocalDataSource(weatherLocalDataSourceImpl: WeatherLocalDataSourceImpl): WeatherLocalDataSource

    @Binds
    fun bindLocationLocalDataSource(locationLocalDataSourceImpl: LocationLocalDataSourceImpl): LocationLocalDataSource

}


@Module
@InstallIn(SingletonComponent::class)
object DataSourceProvidesModule {

    @Provides
    fun provideDeviceLocationDataSource(@ApplicationContext context: Context): DeviceLocationDataSource =
        DeviceLocationDataSourceImpl(context)

}
