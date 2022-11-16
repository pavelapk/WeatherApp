package ru.pavelapk.weatherapp.presentation.weather.adapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.transform
import ru.pavelapk.weatherapp.domain.weather.GetWeatherUseCase
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    val currentWeather = getWeatherUseCase().transform { result ->
        result.onSuccess {
            emit(it)
        }
    }.asLiveData()
}
