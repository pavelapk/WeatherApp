package ru.pavelapk.weatherapp.data.net.weather

import retrofit2.http.GET
import retrofit2.http.Query
import ru.pavelapk.weatherapp.data.net.weather.model.ForecastResponseDto

interface WeatherService {

    @GET("/v1/forecast")
    suspend fun getForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: List<String>,
        @Query("daily") daily: List<String>,
        @Query("current_weather") currentWeather: Boolean,
        @Query("temperature_unit") temperatureUnit: String,
        @Query("windspeed_unit") windspeedUnit: String,
        @Query("timezone") timezone: String,
    ): ForecastResponseDto
}
