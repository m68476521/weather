package com.m68476521.weather.forecast.domain.models

data class Weather(
    val currentWeather: CurrentWeather,
    val daily: Daily,
    val hourly: Hourly,
    val timezone: String,
)
