package ru.pavelapk.weatherapp.presentation.common.utils

import android.app.Activity
import android.content.Context
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager

object KeyboardUtils {
    fun hideKeyboard(activity: Activity, windowToken: IBinder? = null) {
        val token = windowToken ?: activity.currentFocus?.windowToken
        if (token != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(token, 0)
        }
    }

    fun showSoftKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}
