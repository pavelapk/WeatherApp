package ru.pavelapk.weatherapp.presentation.weather.adapter

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.databinding.ItemWeatherCurrentBinding
import ru.pavelapk.weatherapp.presentation.common.adapter.SingleAdapter
import ru.pavelapk.weatherapp.presentation.common.utils.DateTimeUtils
import ru.pavelapk.weatherapp.presentation.common.utils.WeatherUtils
import ru.pavelapk.weatherapp.presentation.weather.model.TodayAndCurrentWeather

class CurrentWeatherAdapter : SingleAdapter<TodayAndCurrentWeather>(R.layout.item_weather_current) {
    override fun createViewHolder(view: View) = CurrentWeatherViewHolder(view)

    inner class CurrentWeatherViewHolder(view: View) :
        SingleViewHolder<TodayAndCurrentWeather>(view) {
        private val binding by viewBinding(ItemWeatherCurrentBinding::bind)

        private val hourlyWeatherAdapter = HourlyWeatherAdapter()

        init {
            binding.recyclerHourlyWeather.adapter = hourlyWeatherAdapter
        }

        override fun bind(data: TodayAndCurrentWeather) = with(binding) {
            val context = root.context
            textViewCurrentDay.text = DateTimeUtils.formatDate(data.today.date)
            textViewCurrentDayTemp.text = context.getString(
                R.string.current_day_temperature,
                data.today.maxTemp,
                data.today.minTemp
            )
            textViewCurrentTime.text = DateTimeUtils.formatTime(data.current.time)
            textViewCurrentTemp.text =
                context.getString(R.string.current_temperature, data.current.temp)

            val imageRes =
                WeatherUtils.getWeatherCodeImage(data.current.weatherCode, data.current.isNight)
            imageViewWeather.setImageDrawable(AppCompatResources.getDrawable(context, imageRes))

            textViewCurrentWeather.text =
                WeatherUtils.getWeatherCodeName(data.current.weatherCode, context.resources)
            textViewCurrentWindSpeed.text = context.getString(
                R.string.current_wind,
                data.current.windSpeed,
                directionToName(data.current.windDirection)
            )
            imageViewWindDirection.rotation = data.current.windDirection.toFloat() + 180

            hourlyWeatherAdapter.submitList(data.hourly)
        }

        private fun directionToName(dir: Double): String {
            val resources = binding.root.context.resources
            val directionNames = resources.getStringArray(R.array.cardinal_directions)

            val i = (dir + 22.5).toInt() % 360 / 45
            return directionNames[i]
        }
    }
}
