package ru.pavelapk.weatherapp.presentation.common.ui

interface State

internal val emptyState: State
    get() = object : State {}
