package ru.pavelapk.weatherapp.presentation.weather

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.databinding.FragmentWeatherBinding
import ru.pavelapk.weatherapp.domain.weather.model.DayWeather
import ru.pavelapk.weatherapp.presentation.weather.adapter.CurrentWeatherAdapter
import ru.pavelapk.weatherapp.presentation.weather.adapter.DailyWeatherAdapter
import ru.pavelapk.weatherapp.presentation.weather.model.WeatherState

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {
    private val binding by viewBinding(FragmentWeatherBinding::bind)
    private val viewModel by viewModels<WeatherViewModel>()

    private val currentWeatherAdapter = CurrentWeatherAdapter()

    private val dailyWeatherAdapter = DailyWeatherAdapter()

    private val concatAdapter = ConcatAdapter(
        currentWeatherAdapter,
        dailyWeatherAdapter
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservers()

        dailyWeatherAdapter.submitList(
            List(10) {
                DayWeather(
                    LocalDate(2022, 11, 15 + it),
                    it,
                    5,
                    -5,
                    LocalDateTime(1, 1, 1, 0, 0),
                    LocalDateTime(1, 1, 1, 0, 0)
                )
            }
        )
    }

    private fun initView() = with(binding) {
        binding.recycler.adapter = concatAdapter
        binding.searchEditText.setText("Томск")
    }

    private fun initObservers() = with(viewModel) {
        state.observe(viewLifecycleOwner) {
            handleState(it)
        }
    }

    private fun handleState(state: WeatherState) = with(binding) {
        currentWeatherAdapter.submitData(state.currentWeather)
        dailyWeatherAdapter.submitList(state.dailyWeather)
    }

    companion object {
        val TAG: String = WeatherFragment::class.java.simpleName

        fun newInstance() = WeatherFragment()
    }
}
