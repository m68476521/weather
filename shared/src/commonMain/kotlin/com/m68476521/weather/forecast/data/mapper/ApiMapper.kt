package com.m68476521.weather.forecast.data.mapper

interface ApiMapper<Domain, Model> {
    fun mapToDomain(
        model: Model,
        timeZone: String = "",
    ): Domain
}
