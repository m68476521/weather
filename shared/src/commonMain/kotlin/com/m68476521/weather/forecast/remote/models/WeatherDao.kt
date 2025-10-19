package com.m68476521.weather.forecast.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDao(
    @SerialName("current")
    val currentDto: CurrentDto = CurrentDto(),
    @SerialName("current_units")
    val currentUnits: CurrentUnits = CurrentUnits(),
    @SerialName("daily")
    val dailyDto: DailyDto = DailyDto(),
    @SerialName("daily_units")
    val dailyUnits: DailyUnits = DailyUnits(),
    @SerialName("elevation")
    val elevation: Int = 0,
    @SerialName("generationtime_ms")
    val generationTimeMs: Double = 0.0,
    @SerialName("hourly")
    val hourlyDto: HourlyDto = HourlyDto(),
    @SerialName("hourly_units")
    val hourlyUnits: HourlyUnits = HourlyUnits(),
    @SerialName("latitude")
    val latitude: Double = 0.0,
    @SerialName("longitude")
    val longitude: Double = 0.0,
    @SerialName("timezone")
    val timezone: String = "",
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String = "",
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int = 0
)