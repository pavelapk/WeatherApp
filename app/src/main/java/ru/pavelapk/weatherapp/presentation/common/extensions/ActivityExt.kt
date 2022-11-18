package ru.pavelapk.weatherapp.presentation.common.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

fun AppCompatActivity.replaceFragment(
    @IdRes containerId: Int,
    fragment: Fragment,
    fragmentTag: String,
    addToBackStack: Boolean = true
) {
    val currentFragment = supportFragmentManager.findFragmentByTag(fragmentTag)
    if (currentFragment?.isVisible == true) return

    supportFragmentManager.commit {
        setReorderingAllowed(true)
        if (addToBackStack) {
            addToBackStack(fragmentTag)
        }
        replace(containerId, fragment, fragmentTag)
    }
}

