package ru.pavelapk.weatherapp.presentation.main

import android.Manifest
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowInsetsControllerCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.presentation.common.extensions.replaceFragment
import ru.pavelapk.weatherapp.presentation.common.extensions.toast
import ru.pavelapk.weatherapp.presentation.common.statusbar.StatusBarColorContract
import ru.pavelapk.weatherapp.presentation.main.model.MainAction
import ru.pavelapk.weatherapp.presentation.search.SearchFragment
import ru.pavelapk.weatherapp.presentation.weather.WeatherFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),
    WeatherFragment.WeatherFragmentListener,
    SearchFragment.SearchFragmentListener,
    StatusBarColorContract {
    private val viewModel: MainViewModel by viewModels()

    private val locationPermissionRequest = registerForActivityResult(
        RequestPermission()
    ) { isGranted ->
        viewModel.onRequestLocationPermissionResult(isGranted)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            goToWeather()
        }

        initObservers()
    }

    private fun initObservers() = with(viewModel) {
        event.observe(this@MainActivity) {
            it.getContentIfNotHandled()?.let { action ->
                when (action) {
                    is MainAction.Error -> toast(action.messageId)
                    MainAction.OpenWeatherScreen -> goToWeather()
                }
            }
        }
    }

    override fun goToSearch() {
        replaceFragment(
            R.id.fragmentContainer,
            SearchFragment.newInstance(),
            SearchFragment.TAG,
            true
        )
    }

    override fun goToWeather() {
        replaceFragment(
            R.id.fragmentContainer,
            WeatherFragment.newInstance(),
            WeatherFragment.TAG,
            false
        )
    }

    override fun requestDeviceLocation() {
        locationPermissionRequest.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    override fun changeStatusBarColor(colorRes: Int, isLight: Boolean) {
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLight
        window.statusBarColor = getColor(colorRes)
    }
}
