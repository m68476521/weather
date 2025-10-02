package com.m68476521.weather.geolocation.data.repository

import com.m68476521.weather.geolocation.domain.models.GeoLocation
import com.m68476521.weather.geolocation.domain.repository.GeoLocationRepository
import com.m68476521.weather.utils.ApiErrorResponse
import com.m68476521.weather.utils.Response
import kotlinx.coroutines.flow.Flow

class GeolocationRepositoryIml() : GeoLocationRepository {

    override val geoLocation: Flow<GeoLocation?>
        get() = TODO("Not yet implemented")

    override suspend fun upsertLocation(geoLocation: GeoLocation) {
        TODO("Not yet implemented")
    }

    override fun fetchGeoLocation(query: String): Flow<Response<List<GeoLocation>, ApiErrorResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun clearGeoLocation() {
        TODO("Not yet implemented")
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }
}