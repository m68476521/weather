package com.m68476521.weather.forecast.domain.repository

import com.m68476521.weather.forecast.domain.models.Weather
import com.m68476521.weather.utils.ApiErrorResponse
import com.m68476521.weather.utils.Response
import kotlinx.coroutines.flow.StateFlow

interface ForecastRepository {
    val weatherData: StateFlow<Response<Weather, ApiErrorResponse>?>

    fun fetchWeatherData(
        latitude: Float,
        longitude: Float,
        timeZone: String,
    )
}
