package ru.pavelapk.weatherapp.presentation.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.databinding.ItemWeatherHourBinding
import ru.pavelapk.weatherapp.domain.weather.model.HourWeather
import ru.pavelapk.weatherapp.presentation.common.adapter.createDiff

class HourlyWeatherAdapter :
    ListAdapter<HourWeather, HourlyWeatherAdapter.HourWeatherViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourWeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather_hour, parent, false)
        return HourWeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: HourWeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HourWeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding by viewBinding(ItemWeatherHourBinding::bind)

        fun bind(data: HourWeather) = with(binding) {
            val context = root.context
            textViewTemp.text = context.getString(R.string.temperature, data.temp)
            // TODO picture
            imageViewWeather.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_weather_cloudy_day
                )
            )
            textViewTime.text = data.time.toString()
        }
    }

    private companion object {
        val DIFF = createDiff<HourWeather> { oldItem, newItem ->
            oldItem.time == newItem.time
        }
    }

}