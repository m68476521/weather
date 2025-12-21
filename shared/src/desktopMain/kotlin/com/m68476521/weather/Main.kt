package com.m68476521.weather

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.m68476521.weather.di.initKoin

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Weather",
        ) {
            val calculatorScreenSize = calculateWindowSizeClass()
            App(
                calculatorScreenSize.widthSizeClass,
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = false,
            )
        }
    }
}
