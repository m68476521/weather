package com.m68476521.weather

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import com.m68476521.weather.ui.components.getNavigationType
import com.m68476521.weather.ui.home.HomeScreen

@Composable
fun App(windowWidthSizeClass: WindowWidthSizeClass) {
    MaterialTheme {
        HomeScreen(navigationType = getNavigationType(windowWidthSizeClass))
    }
}
