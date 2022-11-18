package ru.pavelapk.weatherapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowInsetsControllerCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.presentation.common.extensions.replaceFragment
import ru.pavelapk.weatherapp.presentation.common.statusbar.StatusBarColorContract
import ru.pavelapk.weatherapp.presentation.search.SearchFragment
import ru.pavelapk.weatherapp.presentation.weather.WeatherFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),
    WeatherFragment.WeatherFragmentListener,
    SearchFragment.SearchFragmentListener,
    StatusBarColorContract {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            replaceFragment(
                R.id.fragmentContainer,
                WeatherFragment.newInstance(),
                WeatherFragment.TAG,
                false
            )
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

    override fun changeStatusBarColor(colorRes: Int, isLight: Boolean) {
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLight
        window.statusBarColor = getColor(colorRes)
    }
}