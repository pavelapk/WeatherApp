package ru.pavelapk.weatherapp.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.pavelapk.weatherapp.data.db.AppDatabase
import ru.pavelapk.weatherapp.data.db.Database
import ru.pavelapk.weatherapp.data.db.location.LocationDao
import ru.pavelapk.weatherapp.data.db.weather.CurrentWeatherDao
import ru.pavelapk.weatherapp.data.db.weather.DayWeatherDao
import ru.pavelapk.weatherapp.data.db.weather.HourWeatherDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Database.build(appContext)

    @Provides
    fun provideCurrentWeatherDao(appDatabase: AppDatabase): CurrentWeatherDao =
        appDatabase.getCurrentWeatherDao()

    @Provides
    fun provideDayWeatherDao(appDatabase: AppDatabase): DayWeatherDao =
        appDatabase.getDayWeatherDao()

    @Provides
    fun provideHourWeatherDao(appDatabase: AppDatabase): HourWeatherDao =
        appDatabase.getHourWeatherDao()

    @Provides
    fun provideLocationDao(appDatabase: AppDatabase): LocationDao =
        appDatabase.getLocationDao()
}
