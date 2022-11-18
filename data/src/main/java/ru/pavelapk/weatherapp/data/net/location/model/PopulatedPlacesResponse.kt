package ru.pavelapk.weatherapp.data.net.location.model

import kotlinx.serialization.Serializable

@Serializable
data class PopulatedPlacesResponse(
    val data: List<PopulatedPlaceSummary>
)
