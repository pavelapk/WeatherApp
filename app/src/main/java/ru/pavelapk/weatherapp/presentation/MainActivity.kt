package ru.pavelapk.weatherapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import ru.pavelapk.weatherapp.R
import ru.pavelapk.weatherapp.presentation.weather.WeatherFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            switchFragment(WeatherFragment.newInstance(), WeatherFragment.TAG, false)
        }
    }

    private fun switchFragment(fragment: Fragment, tag: String, backStack: Boolean = true) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            if (backStack) addToBackStack(null)
            replace(R.id.fragmentContainer, fragment, tag)
        }
    }
}