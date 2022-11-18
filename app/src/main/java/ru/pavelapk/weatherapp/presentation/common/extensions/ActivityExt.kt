package ru.pavelapk.weatherapp.presentation.common.extensions

import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

fun AppCompatActivity.replaceFragment(
    @IdRes containerId: Int,
    fragment: Fragment,
    fragmentTag: String,
    addToBackStack: Boolean = true
) {
    supportFragmentManager.commit {
        setReorderingAllowed(true)
        if (addToBackStack) {
            addToBackStack(fragmentTag)
        }
        replace(containerId, fragment, fragmentTag)
    }
}

fun AppCompatActivity.toast(text: String) =
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

fun AppCompatActivity.toast(@StringRes resId: Int) =
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()

fun AppCompatActivity.toastLong(text: String) =
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()

fun AppCompatActivity.toastLong(@StringRes resId: Int) =
    Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
