package com.m68476521.weather.geolocation.domain.repository

import com.m68476521.weather.geolocation.domain.models.GeoLocation
import com.m68476521.weather.utils.ApiErrorResponse
import com.m68476521.weather.utils.Response
import kotlinx.coroutines.flow.Flow

interface GeoLocationRepository {
    val geoLocation: Flow<GeoLocation?>

    suspend fun upsertLocation(geoLocation: GeoLocation)

    fun fetchGeoLocation(query: String): Flow<Response<List<GeoLocation>, ApiErrorResponse>>

    suspend fun clearGeoLocation()

    suspend fun clear()
}
