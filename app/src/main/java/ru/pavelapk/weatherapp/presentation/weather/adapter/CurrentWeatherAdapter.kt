package ru.pavelapk.weatherapp.presentation.weather.adapter

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.databinding.ItemWeatherCurrentBinding
import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import ru.pavelapk.weatherapp.presentation.common.adapter.SingleAdapter

class CurrentWeatherAdapter : SingleAdapter<CurrentWeather>(R.layout.item_weather_current) {
    override fun createViewHolder(view: View) = CurrentWeatherViewHolder(view)

    inner class CurrentWeatherViewHolder(view: View) : SingleViewHolder<CurrentWeather>(view) {
        private val binding by viewBinding(ItemWeatherCurrentBinding::bind)

        override fun bind(data: CurrentWeather) = with(binding) {
            textViewCurrentTemp.text = "${data.temp} °C"
        }

    }
}