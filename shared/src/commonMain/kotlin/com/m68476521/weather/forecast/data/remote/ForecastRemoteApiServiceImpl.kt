package com.m68476521.weather.forecast.data.remote

import com.m68476521.weather.common.data.safeRequest
import com.m68476521.weather.forecast.data.remote.models.WeatherDto
import com.m68476521.weather.utils.ApiErrorResponse
import com.m68476521.weather.utils.Response
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import kotlinx.coroutines.flow.Flow
import org.hoods.forecastly.utils.ApiParameters
import org.hoods.forecastly.utils.K

class ForecastRemoteApiServiceImpl(
    private val httpClient: HttpClient,
) : ForecastRemoteApiService {
    override fun fetchForecast(
        latitude: Float,
        longitude: Float,
        daily: Array<String>,
        currentWeather: Array<String>,
        hourlyWeather: Array<String>,
        timeformat: String,
        timeZone: String,
    ): Flow<Response<WeatherDto, ApiErrorResponse>> =
        httpClient.safeRequest {
            url(urlString = "${K.FORECAST_BASE_URL}/${K.FORECAST_END_POINT}")
            parameter(ApiParameters.LATITUDE, latitude.toString())
            parameter(ApiParameters.LONGITUDE, longitude.toString())
            parameter(ApiParameters.DAILY, daily.joinToString(","))
            parameter(ApiParameters.CURRENT_WEATHER, currentWeather.joinToString(","))
            parameter(ApiParameters.HOURLY, hourlyWeather.joinToString(","))
            parameter(ApiParameters.TIME_FORMAT, timeformat)
            parameter(ApiParameters.TIME_ZONE, timeZone)
        }
}
