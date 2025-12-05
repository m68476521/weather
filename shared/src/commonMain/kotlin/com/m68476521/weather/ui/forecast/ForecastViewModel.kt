package com.m68476521.weather.ui.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m68476521.weather.forecast.domain.models.Daily
import com.m68476521.weather.forecast.domain.models.Weather
import com.m68476521.weather.forecast.domain.repository.ForecastRepository
import com.m68476521.weather.geolocation.domain.models.GeoLocation
import com.m68476521.weather.geolocation.domain.repository.GeoLocationRepository
import com.m68476521.weather.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.hoods.forecastly.utils.Util

class ForecastViewModel(
    private val repository: ForecastRepository,
    private val geoLocationRepository: GeoLocationRepository,
) : ViewModel() {
    private val _forecast = MutableStateFlow(ForecastState())

    val forecast = _forecast.asStateFlow()

    init {
        getGeolocation()
    }

    init {
        observeWeatherData()
    }

    private fun observeWeatherData() =
        viewModelScope.launch {
            repository.weatherData.collect { response ->
                when (response) {
                    is Response.Loading -> {
                        _forecast.update {
                            it.copy(isLoading = true, error = null)
                        }
                    }

                    is Response.Success -> {
                        _forecast.update {
                            it.copy(isLoading = false, error = null, weather = response.data)
                        }

                        val todayDailyWeatherInfo =
                            response.data.daily.weatherInfo.find {
                                Util.isTodayDate(it.time)
                            }

                        _forecast.update {
                            it.copy(dailyWeatherInfo = todayDailyWeatherInfo)
                        }
                    }

                    is Response.Error.DefaultError -> {
                        _forecast.update {
                            it.copy(isLoading = false, error = "Error Ocurred")
                        }
                    }

                    is Response.Error.NetworkError -> {
                        _forecast.update {
                            it.copy(isLoading = false, error = "No Network")
                        }
                    }

                    is Response.Error.SerializationError -> {
                        _forecast.update {
                            it.copy(isLoading = false, error = "Failed to Serialize Data")
                        }
                    }

                    is Response.Error.HttpError -> {
                        _forecast.update {
                            it.copy(isLoading = false, error = response.code.toString())
                        }
                    }

                    else -> {}
                }
            }
        }

    fun getGeolocation() =
        viewModelScope.launch {
            geoLocationRepository.geoLocation.collect {
                _forecast.update { state ->
                    state.copy(selectedLocation = it)
                }
            }
        }

    fun fetchWeatherData() {
        viewModelScope.launch {
            forecast.value.selectedLocation?.let { geoLocation ->
                repository.fetchWeatherData(
                    // TODO check why this is nullable
                    latitude = geoLocation.latitude?.toFloat() ?: 0.toFloat(),
                    longitude = geoLocation.longitude?.toFloat() ?: 0.toFloat(),
                    timeZone = geoLocation.timeZone ?: "America/Los_Angeles",
                )
            }
        }
    }
}

data class ForecastState(
    val weather: Weather? = null,
    val error: String? = null,
    val isLoading: Boolean = false,
    val dailyWeatherInfo: Daily.WeatherInfo? = null,
    val selectedLocation: GeoLocation? = null,
)
