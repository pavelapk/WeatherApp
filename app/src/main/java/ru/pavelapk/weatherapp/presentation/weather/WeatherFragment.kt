package ru.pavelapk.weatherapp.presentation.weather

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.databinding.FragmentWeatherBinding
import ru.pavelapk.weatherapp.presentation.common.extensions.toast
import ru.pavelapk.weatherapp.presentation.weather.adapter.CurrentWeatherAdapter
import ru.pavelapk.weatherapp.presentation.weather.adapter.DailyWeatherAdapter
import ru.pavelapk.weatherapp.presentation.weather.model.WeatherAction
import ru.pavelapk.weatherapp.presentation.weather.model.WeatherScreenState
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

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        viewModel.onRequestLocationPermissionResult(isGranted)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservers()
    }

    private fun initView() = with(binding) {
        binding.recycler.adapter = concatAdapter

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshWeather()
        }

        binding.searchTextInputLayout.setOnClickListener {
            toast("alo")
        }
    }

    private fun initObservers() = with(viewModel) {
        weatherState.observe(viewLifecycleOwner) {
            handleWeatherState(it)
        }
        state.observe(viewLifecycleOwner) {
            handleState(it)
        }
        event.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { action ->
                when (action) {
                    WeatherAction.RequestLocationPermission -> locationPermissionRequest.launch(
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                    WeatherAction.LocationPermissionNotGranted -> toast(R.string.location_permission_not_granted)
                    is WeatherAction.Error -> toast(action.messageId)
                }
            }
        }
    }

    private fun handleWeatherState(state: WeatherState) = with(binding) {
        currentWeatherAdapter.submitData(state.currentWeather)
        dailyWeatherAdapter.submitList(state.dailyWeather)
    }

    private fun handleState(state: WeatherScreenState) = with(binding) {
        swipeRefresh.isRefreshing = state.isLoading
        searchEditText.setText(state.locationName)
    }

    companion object {
        val TAG: String = WeatherFragment::class.java.simpleName

        fun newInstance() = WeatherFragment()
    }
}
