package ru.pavelapk.weatherapp.presentation.common.statusbar

import androidx.annotation.ColorRes

interface StatusBarColorContract {
    fun changeStatusBarColor(
        @ColorRes
        colorRes: Int,
        isLight: Boolean
    )
}
