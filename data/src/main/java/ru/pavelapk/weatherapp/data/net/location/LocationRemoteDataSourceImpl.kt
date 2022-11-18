package ru.pavelapk.weatherapp.data.net.location

import android.location.Location.FORMAT_DEGREES
import android.location.Location.convert
import ru.pavelapk.weatherapp.data.net.location.model.toDomain
import ru.pavelapk.weatherapp.domain.location.datasource.LocationRemoteDataSource
import ru.pavelapk.weatherapp.domain.location.model.Coordinates
import ru.pavelapk.weatherapp.domain.location.model.Location
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import javax.inject.Inject

class LocationRemoteDataSourceImpl @Inject constructor(
    private val locationService: LocationService
) : LocationRemoteDataSource {

    override suspend fun findCities(query: String): List<Location> {
        val response = locationService.findCities(
            namePrefix = query,
            types = Types,
            languageCode = LanguageCode,
            limit = FindCitiesLimit,
            sort = FindCitiesSort
        )
        return response.data.map { it.toDomain() }
    }

    override suspend fun findLocationByCoordinates(coordinates: Coordinates): Location? {
        convert(coordinates.latitude, FORMAT_DEGREES)
        val response = locationService.findCitiesNearLocation(
            locationId = convertCoordinates(coordinates),
            types = Types,
            languageCode = LanguageCode,
            limit = 1,
        )
        return response.data.getOrNull(0)?.toDomain()
    }

    private fun convertCoordinates(coordinates: Coordinates): String {
        val symbols = DecimalFormatSymbols()
        symbols.decimalSeparator = '.'
        val df = DecimalFormat("###.#####", symbols)
        df.positivePrefix = "+"
        df.decimalFormatSymbols = symbols
        return df.format(coordinates.latitude) + df.format(coordinates.longitude)
    }

    private companion object {
        const val Types = "CITY"
        const val LanguageCode = "ru"
        const val FindCitiesLimit = 10
        const val FindCitiesSort = "-population" // sort by population desc
    }
}
