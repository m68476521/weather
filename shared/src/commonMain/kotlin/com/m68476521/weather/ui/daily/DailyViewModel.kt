package com.m68476521.weather.ui.daily

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m68476521.weather.forecast.domain.models.Daily
import com.m68476521.weather.forecast.domain.repository.ForecastRepository
import com.m68476521.weather.geolocation.domain.models.GeoLocation
import com.m68476521.weather.geolocation.domain.repository.GeoLocationRepository
import com.m68476521.weather.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DailyViewModel(
    private val repository: ForecastRepository,
    private val geoLocationRepository: GeoLocationRepository,
) : ViewModel() {
    private var _dailyState = MutableStateFlow(DailyState())
    val dailyState = _dailyState.asStateFlow()

    init {
        observeWeatherData()
    }

    private fun observeWeatherData() =
        viewModelScope.launch {
            repository.weatherData.collect { response ->
                when (response) {
                    is Response.Loading -> {
                        _dailyState.update {
                            it.copy(isLoading = true, error = null)
                        }
                    }
                    is Response.Success -> {
                        _dailyState.update {
                            it.copy(
                                isLoading = false,
                                daily = response.data.daily,
                                error = null,
                            )
                        }
                    }

                    is Response.Error.DefaultError -> {
                        _dailyState.update {
                            it.copy(isLoading = false, error = "Error Occurred")
                        }
                    }

                    is Response.Error.NetworkError -> {
                        _dailyState.update {
                            it.copy(isLoading = false, error = "No Network")
                        }
                    }

                    is Response.Error.SerializationError -> {
                        _dailyState.update {
                            it.copy(isLoading = false, error = "Failed to Serialize Data")
                        }
                    }

                    is Response.Error.HttpError -> {
                        _dailyState.update {
                            it.copy(isLoading = false, error = response.code.toString())
                        }
                    }

                    else -> { }
                }
            }
        }
}

data class DailyState(
    val daily: Daily? = null,
    val error: String? = null,
    val isLoading: Boolean = false,
    val geoLocation: GeoLocation? = null,
)
