package com.m68476521.weather.geolocation.data.remote.models

import GeoLocationDto
import com.m68476521.weather.common.data.safeRequest
import com.m68476521.weather.geolocation.data.remote.GeoLocationRemoteApiService
import com.m68476521.weather.utils.ApiErrorResponse
import com.m68476521.weather.utils.Response
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import kotlinx.coroutines.flow.Flow
import org.hoods.forecastly.utils.K

class GeoLocationRemoteApiServiceImpl(
    private val httpClient: HttpClient,
) : GeoLocationRemoteApiService {
    override fun searchLocation(query: String): Flow<Response<GeoLocationDto, ApiErrorResponse>> =
        httpClient.safeRequest<GeoLocationDto, ApiErrorResponse> {
            url(urlString = K.GEO_CODING_BASE_URL + "/${K.GEO_CODING_END_POINT}")
            parameter("name", query)
        }
}
