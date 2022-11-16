package ru.pavelapk.weatherapp.presentation.weather.adapter

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.databinding.ItemWeatherCurrentBinding
import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import ru.pavelapk.weatherapp.presentation.common.adapter.SingleAdapter
import ru.pavelapk.weatherapp.presentation.common.utils.DateTimeUtils
import ru.pavelapk.weatherapp.presentation.common.utils.WeatherUtils
import ru.pavelapk.weatherapp.presentation.weather.model.HourWeatherDayNight

class CurrentWeatherAdapter : SingleAdapter<CurrentWeather>(R.layout.item_weather_current) {
    override fun createViewHolder(view: View) = CurrentWeatherViewHolder(view)

    inner class CurrentWeatherViewHolder(view: View) : SingleViewHolder<CurrentWeather>(view) {
        private val binding by viewBinding(ItemWeatherCurrentBinding::bind)

        private val hourlyWeatherAdapter = HourlyWeatherAdapter()

        init {
            binding.recyclerHourlyWeather.adapter = hourlyWeatherAdapter
        }

        override fun bind(data: CurrentWeather) = with(binding) {
            val context = root.context
            textViewCurrentDay.text = DateTimeUtils.formatDate(data.currentDayWeather.date)
            textViewCurrentDayTemp.text = context.getString(
                R.string.current_day_temperature,
                data.currentDayWeather.maxTemp,
                data.currentDayWeather.minTemp
            )
            textViewCurrentTime.text = DateTimeUtils.formatTime(data.time)
            textViewCurrentTemp.text = context.getString(R.string.current_temperature, data.temp)

            val isNight = WeatherUtils.isNight(
                data.time,
                data.currentDayWeather.sunrise,
                data.currentDayWeather.sunset
            )
            val imageRes = WeatherUtils.getWeatherCodeImage(data.weatherCode, isNight)
            imageViewWeather.setImageDrawable(AppCompatResources.getDrawable(context, imageRes))

            textViewCurrentWeather.text =
                WeatherUtils.getWeatherCodeName(data.weatherCode, context.resources)
            textViewCurrentWindSpeed.text = context.getString(
                R.string.current_wind,
                data.windSpeed,
                directionToName(data.windDirection)
            )
            imageViewWindDirection.rotation = data.windDirection.toFloat() + 180

            hourlyWeatherAdapter.submitList(data.hourlyWeather.map {
                HourWeatherDayNight(
                    hourWeather = it,
                    isNight = WeatherUtils.isNight(
                        it.time,
                        data.currentDayWeather.sunrise,
                        data.currentDayWeather.sunset
                    )
                )
            })
        }

        private fun directionToName(dir: Double): String {
            val resources = binding.root.context.resources
            val directionNames = resources.getStringArray(R.array.cardinal_directions)

            val i = (dir + 22.5).toInt() % 360 / 45
            return directionNames[i]
        }
    }
}