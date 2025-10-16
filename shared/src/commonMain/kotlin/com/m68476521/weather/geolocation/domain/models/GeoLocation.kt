package com.m68476521.weather.geolocation.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.hoods.forecastly.utils.FlagUrl

@Serializable
data class GeoLocation(
    val id: Int = 0,
    val name: String ? = null,
    val countryName: String? = null,
    @SerialName("country_code")
    val countryCode: String? = null,
    val flagUrl: FlagUrl? = null,
    @SerialName("country_id")
    val countryId: Int? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val timeZone: String? = null,
    val elevation: Double? = null,
    @SerialName("feature_code")
    val featureCode: String? = null,
    val population : String? = null,
    val country : String? = null,
)
