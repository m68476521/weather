package com.m68476521.weather.forecast.data.mapper

import com.m68476521.weather.forecast.data.remote.models.WeatherDto
import com.m68476521.weather.forecast.domain.models.Weather

class ApiWeatherMapper(
    private val apiDailyWeatherMapper: ApiDailyWeatherMapper,
    private val currentWeatherMapper: CurrentWeatherMapper,
    private val apiHourlyMapper: ApiHourlyMapper,
) : ApiMapper<Weather, WeatherDto> {
    override fun mapToDomain(
        model: WeatherDto,
        timeZone: String,
    ): Weather =
        Weather(
            currentWeather = currentWeatherMapper.mapToDomain(model.current, timeZone),
            daily = apiDailyWeatherMapper.mapToDomain(model.daily, timeZone),
            hourly = apiHourlyMapper.mapToDomain(model.hourly, timeZone),
            timezone = timeZone,
        )
}
