package ru.pavelapk.weatherapp.data.net.location

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.pavelapk.weatherapp.data.net.location.model.PopulatedPlacesResponse

interface LocationService {

    @GET("/v1/geo/cities")
    suspend fun findCities(
        @Query("namePrefix") namePrefix: String,
        @Query("types") types: String,
        @Query("languageCode") languageCode: String,
        @Query("limit") limit: Int,
        @Query("sort") sort: String,
    ): PopulatedPlacesResponse

    @GET("/v1/geo/locations/{locationId}/nearbyCities")
    suspend fun findCitiesNearLocation(
        @Path("locationId") locationId: String,
        @Query("types") types: String,
        @Query("languageCode") languageCode: String,
        @Query("limit") limit: Int
    ): PopulatedPlacesResponse
}
