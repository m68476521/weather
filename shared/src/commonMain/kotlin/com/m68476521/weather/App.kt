package com.m68476521.weather

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import com.m68476521.weather.ui.components.getNavigationType
import com.m68476521.weather.ui.navigation.ForecastNavigation
import com.m68476521.weather.ui.theme.AppTheme

@Composable
fun App(
    windowWidthSizeClass: WindowWidthSizeClass,
    dynamicColor: Boolean,
    darkTheme: Boolean,
) {
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        ForecastNavigation(navigationType = getNavigationType(windowWidthSizeClass))
    }
}
