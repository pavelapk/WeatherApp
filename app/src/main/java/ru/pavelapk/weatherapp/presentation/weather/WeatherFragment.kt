package ru.pavelapk.weatherapp.presentation.weather

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.databinding.FragmentWeatherBinding
import ru.pavelapk.weatherapp.domain.weather.model.CurrentWeather
import ru.pavelapk.weatherapp.domain.weather.model.DayWeather
import ru.pavelapk.weatherapp.presentation.weather.adapter.CurrentWeatherAdapter

class WeatherFragment : Fragment(R.layout.fragment_weather) {
    private val binding by viewBinding(FragmentWeatherBinding::bind)

    private val currentWeatherAdapter = CurrentWeatherAdapter()

    private val concatAdapter = ConcatAdapter(
        currentWeatherAdapter
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.adapter = concatAdapter

        binding.searchEditText.setText("Томск")
        currentWeatherAdapter.submitData(
            CurrentWeather(
                DayWeather(LocalDate(2022, 11, 15), 0, 5, -5),
                LocalDateTime(2022, 11, 15, 19, 16), 2, 0, 4.7, 190.0,
            )
        )


    }

    companion object {
        val TAG: String = WeatherFragment::class.java.simpleName

        fun newInstance() = WeatherFragment()
    }
}