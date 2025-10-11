package com.m68476521.weather.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m68476521.weather.geolocation.domain.models.GeoLocation
import com.m68476521.weather.geolocation.domain.repository.GeoLocationRepository
import com.m68476521.weather.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val geoLocationRepository: GeoLocationRepository,
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeUIState())
    val homeState = _homeState.asStateFlow()

    init {
        getGeolocation()
    }

    fun getGeolocation() =
        viewModelScope.launch {
            geoLocationRepository.geoLocation
                .collect {
                    _homeState.update { state ->
                        state.copy(
                            selectedLocation = it,
                            isLocationSelected = it != null,
                        )
                    }
                }
        }

    fun saveFavouriteLocation() =
        viewModelScope.launch {
            homeState.value.selectedLocation?.let {
                geoLocationRepository.upsertLocation(it)
            }
        }

    fun setSelectedLocation(geoLocation: GeoLocation) =
        viewModelScope.launch {
            _homeState.update {
                it.copy(
                    selectedLocation = geoLocation.copy(id = 1),
                    isLocationSelected = true,
                )
            }
        }

    fun fetchGeoLocations(query: String) {
        viewModelScope.launch {
            geoLocationRepository.fetchGeoLocation(query).collect { result ->
                when (result) {
                    is Response.Success -> {
                        _homeState.update {
                            it.copy(
                                isLoading = false,
                                error = null,
                                geoLocations = result.data,
                            )
                        }
                    }

                    is Response.Error.DefaultError -> {
                        _homeState.update {
                            it.copy(
                                isLoading = false,
                                error = "Error Occurred",
                            )
                        }
                    }

                    is Response.Error.NetworkError -> {
                        _homeState.update {
                            it.copy(
                                isLoading = false,
                                error = "No Network",
                            )
                        }
                    }

                    is Response.Error.SerializationError -> {
                        _homeState.update {
                            it.copy(
                                isLoading = false,
                                error = "Failed to Serialize Data",
                            )
                        }
                    }

                    is Response.Error.HttpError -> {
                        _homeState.update {
                            it.copy(
                                isLoading = false,
                                error = result.code.toString(),
                            )
                        }
                    }

                    is Response.Loading -> {
                        _homeState.update {
                            it.copy(
                                isLoading = false,
                                error = null,
                            )
                        }
                    }
                }
            }
        }
    }
}

data class HomeUIState(
    val isLocationSelected: Boolean = false,
    val selectedLocation: GeoLocation? = null,
    val query: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val geoLocations: List<GeoLocation> = emptyList(),
)
