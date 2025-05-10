package com.m68476521.weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.datetime.Month

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Weather",
    ) {
        //TODO check this
//        App()
//getPlatform()
//        Greeting()
//        Box(modifier = Modifier.fillMaxSize()) {
//            Greeting()
//        }
        Welcome()
    }
}


@Composable
fun Welcome() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Greeting()
    }
}