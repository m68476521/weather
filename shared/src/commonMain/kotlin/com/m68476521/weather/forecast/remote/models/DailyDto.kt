package com.m68476521.weather.forecast.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyDto(
    @SerialName("sunrise")
    val sunrise: List<String> = listOf(),
    @SerialName("sunset")
    val sunset: List<String> = listOf(),
    @SerialName("temperature_2m_max")
    val temperature2mMax: List<Double> = listOf(),
    @SerialName("temperature_2m_min")
    val temperature2mMin: List<Double> = listOf(),
    @SerialName("time")
    val time: List<Long> = listOf(),
    @SerialName("uv_index_max")
    val uvIndexMax: List<Double> = listOf(),
    @SerialName("weather_code")
    val weatherCode: List<Int> = listOf()
)