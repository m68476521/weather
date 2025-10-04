package com.m68476521.weather.geolocation.data.remote

import GeoLocationDto
import com.m68476521.weather.utils.ApiErrorResponse
import com.m68476521.weather.utils.Response
import kotlinx.coroutines.flow.Flow

interface GeoLocationRemoteApiService {
    fun searchLocation(query: String): Flow<Response<GeoLocationDto, ApiErrorResponse>>
}