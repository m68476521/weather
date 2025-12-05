package com.m68476521.weather.forecast.data.repository

import com.m68476521.weather.forecast.data.mapper.ApiWeatherMapper
import com.m68476521.weather.forecast.data.remote.ForecastRemoteApiService
import com.m68476521.weather.forecast.domain.models.Weather
import com.m68476521.weather.forecast.domain.repository.ForecastRepository
import com.m68476521.weather.utils.ApiErrorResponse
import com.m68476521.weather.utils.Response
import com.m68476521.weather.utils.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class ForecastRepositoryImpl(
    private val forecastRemoteApiService: ForecastRemoteApiService,
    private val mapper: ApiWeatherMapper,
    private val externalScope: CoroutineScope,
) : ForecastRepository {
    private val _weatherData = MutableStateFlow<Response<Weather, ApiErrorResponse>?>(null)
    override val weatherData: StateFlow<Response<Weather, ApiErrorResponse>?>
        get() = _weatherData.asStateFlow()

    override fun fetchWeatherData(
        latitude: Float,
        longitude: Float,
        timeZone: String,
    ) {
        forecastRemoteApiService
            .fetchForecast(
                latitude = latitude,
                longitude = longitude,
                timeZone = timeZone,
            ).map { response ->
                response.map {
                    mapper.mapToDomain(it, timeZone = timeZone)
                }
            }.onEach { result ->
                _weatherData.update {
                    result
                }
            }.launchIn(externalScope)
    }
}
