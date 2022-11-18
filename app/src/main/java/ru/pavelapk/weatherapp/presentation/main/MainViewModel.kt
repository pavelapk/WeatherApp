package ru.pavelapk.weatherapp.presentation.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.domain.location.GetDeviceLocationUseCase
import ru.pavelapk.weatherapp.domain.location.UpdateCurrentLocationUseCase
import ru.pavelapk.weatherapp.presentation.common.mappers.ErrorMappers
import ru.pavelapk.weatherapp.presentation.common.ui.AppViewModel
import ru.pavelapk.weatherapp.presentation.main.model.MainAction
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDeviceLocationUseCase: GetDeviceLocationUseCase,
    private val updateCurrentLocationUseCase: UpdateCurrentLocationUseCase
) : AppViewModel<MainAction>() {

    fun onRequestLocationPermissionResult(isGranted: Boolean) {
        if (isGranted) {
            viewModelScope.launch {
                getAndSaveDeviceLocation()
            }
        } else {
            postEvent(MainAction.Error(R.string.location_permission_not_granted))
        }
    }

    private suspend fun getAndSaveDeviceLocation() {
        getDeviceLocationUseCase()
            .onSuccess {
                updateCurrentLocationUseCase(it)
                postEvent(MainAction.OpenWeatherScreen)
            }.onFailure {
                postEvent(MainAction.Error(ErrorMappers.errorToStringRes(it)))
            }
    }
}
