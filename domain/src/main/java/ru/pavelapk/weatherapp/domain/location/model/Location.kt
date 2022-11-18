package ru.pavelapk.weatherapp.domain.location.model

data class Location(
    val name: String,
    val regionName: String,
    val coordinates: Coordinates
)
