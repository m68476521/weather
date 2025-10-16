package com.m68476521.weather.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.m68476521.weather.App

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val calculateWindowSizes = calculateWindowSizeClass(this)
            App(calculateWindowSizes.widthSizeClass)
        }
    }
}

@Suppress("ktlint:standard:function-signature")
@Preview
@Composable
fun DefaultPreview() {
    App(
        WindowWidthSizeClass.Medium,
    )
}
