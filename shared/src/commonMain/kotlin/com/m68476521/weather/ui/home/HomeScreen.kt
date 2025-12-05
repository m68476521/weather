package com.m68476521.weather.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import com.m68476521.weather.ui.components.NavigationType
import com.m68476521.weather.ui.components.SearchLocationContent
import com.m68476521.weather.ui.forecast.ForecastScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigationType: NavigationType,
    homeViewModel: HomeViewModel = koinViewModel(),
) {
    val state by homeViewModel.homeState.collectAsStateWithLifecycle()
    val (search, onSearchChange) = remember { mutableStateOf("") }
    val context = LocalPlatformContext.current
    var showSearchLocation by rememberSaveable { mutableStateOf(false) }

    AnimatedVisibility(showSearchLocation, modifier = modifier) {
        SearchLocationContent(
            modifier = modifier,
            navigationType = navigationType,
            state = state,
            search = search,
            context = context,
            onSearchChange = onSearchChange,
            onFavouriteClick = {
                homeViewModel.setSelectedLocation(it)
                homeViewModel.saveFavouriteLocation()
                homeViewModel.getGeolocation()
                showSearchLocation = !showSearchLocation
            },
            onSubmit = {
                homeViewModel.fetchGeoLocations(search)
            },
            onNavigateBack = {
                showSearchLocation = false
            },
        )
    }

    AnimatedVisibility(!showSearchLocation, modifier = modifier) {
        if (state.isLocationSelected) {
            ForecastScreen(
                modifier = modifier,
                onSearchClick = {
                    showSearchLocation = !showSearchLocation
                },
            )
        } else {
            Text(text = "Please Select a location to get started")
        }
    }
}
