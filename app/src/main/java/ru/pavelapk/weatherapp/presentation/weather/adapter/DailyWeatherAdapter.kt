package ru.pavelapk.weatherapp.presentation.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.databinding.ItemWeatherDayBinding
import ru.pavelapk.weatherapp.domain.weather.model.DayWeather
import ru.pavelapk.weatherapp.presentation.common.adapter.createDiff

class DailyWeatherAdapter :
    ListAdapter<DayWeather, DailyWeatherAdapter.DayWeatherViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayWeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather_day, parent, false)
        return DayWeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayWeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DayWeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding by viewBinding(ItemWeatherDayBinding::bind)

        fun bind(data: DayWeather) = with(binding) {
            val context = root.context

            textViewDate.text = data.date.toString()
            textViewWeather.text = data.weatherCode.toString()
            textViewMaxTemp.text = context.getString(R.string.temperature, data.maxTemp)
            textViewMinTemp.text = context.getString(R.string.temperature, data.minTemp)
            // TODO picture
            imageViewWeather.setImageDrawable(
                AppCompatResources.getDrawable(
                    root.context,
                    R.drawable.ic_weather_cloudy_day
                )
            )
        }
    }

    private companion object {
        val DIFF = createDiff<DayWeather> { oldItem, newItem ->
            oldItem.date == newItem.date
        }
    }

}