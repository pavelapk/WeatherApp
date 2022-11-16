package ru.pavelapk.weatherapp.presentation.weather.adapter

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.databinding.ItemWeatherCurrentBinding
import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import ru.pavelapk.weatherapp.presentation.common.adapter.SingleAdapter

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
            textViewCurrentDay.text = data.currentDayWeather.date.toString()
            textViewCurrentDayTemp.text = context.getString(
                R.string.current_day_temperature,
                data.currentDayWeather.maxTemp,
                data.currentDayWeather.minTemp
            )
            textViewCurrentTime.text = data.time.toString()
            textViewCurrentTemp.text = context.getString(R.string.current_temperature, data.temp)
            // TODO picture
            imageViewWeather.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_weather_cloudy_day
                )
            )
            textViewCurrentWeather.text = data.weatherCode.toString()
            textViewCurrentWindSpeed.text = context.getString(
                R.string.current_wind,
                data.windSpeed,
                "ЮЗ" /* TODO */
            )
            imageViewWindDirection.rotation = data.windDirection.toFloat()

            hourlyWeatherAdapter.submitList(data.hourlyWeather)
        }

    }
}