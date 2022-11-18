package ru.pavelapk.weatherapp.presentation.search.model

import ru.pavelapk.weatherapp.domain.location.model.Location
import ru.pavelapk.weatherapp.presentation.common.ui.State

data class SearchState(
    val results: List<Location> = listOf()
) : State
