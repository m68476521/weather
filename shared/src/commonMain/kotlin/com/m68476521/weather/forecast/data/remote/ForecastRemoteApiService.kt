package com.m68476521.weather.forecast.data.remote

import com.m68476521.weather.forecast.data.remote.models.WeatherDto
import com.m68476521.weather.utils.ApiErrorResponse
import com.m68476521.weather.utils.Response
import kotlinx.coroutines.flow.Flow

interface ForecastRemoteApiService {
    fun fetchForecast(
        latitude: Float = -6.8f,
        longitude: Float = 39.28f,
        daily: Array<String> =
            arrayOf(
                "weather_code",
                "temperature_2m_max",
                "temperature_2m_min",
                "wind_speed_10m_max",
                "wind_direction_10m_dominant",
                "sunrise",
                "sunset",
                "uv_index_max",
            ),
        currentWeather: Array<String> =
            arrayOf(
                "temperature_2m",
                "is_day",
                "weather_code",
                "wind_speed_10m",
                "wind_direction_10m",
            ),
        hourlyWeather: Array<String> =
            arrayOf(
                "weather_code",
                "temperature_2m",
            ),
        timeformat: String = "unixtime",
        timeZone: String = "Africa/Dar_es_Salaam",
    ): Flow<Response<WeatherDto, ApiErrorResponse>>
}
