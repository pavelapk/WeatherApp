package ru.pavelapk.weatherapp.presentation.common.utils

import android.content.res.Resources
import androidx.annotation.DrawableRes
import kotlinx.datetime.LocalDateTime
import ru.pavelapk.weatherapp.R

object WeatherUtils {

    fun getWeatherCodeName(weatherCode: Int, resources: Resources): String {
        val codes = resources.getStringArray(R.array.weather_codes)

        for (code in codes) {
            val codeAndName = code.split('|')
            if (codeAndName.getOrNull(0)?.toIntOrNull() == weatherCode && codeAndName.size >= 2) {
                return codeAndName[1]
            }
        }

        return resources.getString(R.string.unknown_weather_code)
    }

    @DrawableRes
    fun getWeatherCodeImage(weatherCode: Int, isNight: Boolean): Int {
        return if (isNight) {
            when (weatherCode) {
                0 -> R.drawable.ic_weather_clear_night
                1 -> R.drawable.ic_weather_fair_night
                2 -> R.drawable.ic_weather_cloudy_2_night
                3 -> R.drawable.ic_weather_cloudy
                in 40..49 -> R.drawable.ic_weather_fog
                51, 56 -> R.drawable.ic_weather_rainy_1_night
                53, 55, 57 -> R.drawable.ic_weather_rainy_2_night
                61 -> R.drawable.ic_weather_rainy_3_night
                63, 65 -> R.drawable.ic_weather_rainy_3
                66 -> R.drawable.ic_weather_rain_and_snow_mix
                67 -> R.drawable.ic_weather_snow_and_sleet_mix
                71 -> R.drawable.ic_weather_snowy_1_night
                73 -> R.drawable.ic_weather_snowy_2_night
                75 -> R.drawable.ic_weather_snowy_3
                77 -> R.drawable.ic_weather_hail
                80, 81, 82 -> R.drawable.ic_weather_rainy_3
                85, 86 -> R.drawable.ic_weather_rain_and_snow_mix
                95 -> R.drawable.ic_weather_scattered_thunderstorms_night
                96 -> R.drawable.ic_weather_thunderstorms
                99 -> R.drawable.ic_weather_severe_thunderstorm
                else -> R.drawable.ic_weather_unknown
            }
        } else {
            when (weatherCode) {
                0 -> R.drawable.ic_weather_clear_day
                1 -> R.drawable.ic_weather_fair_day
                2 -> R.drawable.ic_weather_cloudy_2_day
                3 -> R.drawable.ic_weather_cloudy
                in 40..49 -> R.drawable.ic_weather_fog
                51, 56 -> R.drawable.ic_weather_rainy_1_day
                53, 55, 57 -> R.drawable.ic_weather_rainy_2_day
                61 -> R.drawable.ic_weather_rainy_3_day
                63, 65 -> R.drawable.ic_weather_rainy_3
                66 -> R.drawable.ic_weather_rain_and_snow_mix
                67 -> R.drawable.ic_weather_snow_and_sleet_mix
                71 -> R.drawable.ic_weather_snowy_1_day
                73 -> R.drawable.ic_weather_snowy_2_day
                75 -> R.drawable.ic_weather_snowy_3
                77 -> R.drawable.ic_weather_hail
                80, 81, 82 -> R.drawable.ic_weather_rainy_3
                85, 86 -> R.drawable.ic_weather_rain_and_snow_mix
                95 -> R.drawable.ic_weather_scattered_thunderstorms_day
                96 -> R.drawable.ic_weather_thunderstorms
                99 -> R.drawable.ic_weather_severe_thunderstorm
                else -> R.drawable.ic_weather_unknown
            }
        }
    }

    fun isNight(time: LocalDateTime, sunrise: LocalDateTime, sunset: LocalDateTime) =
        time !in sunrise..sunset
}