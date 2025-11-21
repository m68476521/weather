package com.m68476521.weather.forecast.data.mapper

import com.m68476521.weather.forecast.data.remote.models.CurrentDto
import com.m68476521.weather.forecast.domain.models.CurrentWeather
import org.hoods.forecastly.utils.Util
import org.hoods.forecastly.utils.WeatherInfoItem

class CurrentWeatherMapper : ApiMapper<CurrentWeather, CurrentDto> {
    override fun mapToDomain(
        model: CurrentDto,
        timeZone: String,
    ): CurrentWeather =
        CurrentWeather(
            temperature = model.temperature2m,
            time = parseTime(model.time, timeZone),
            weatherStatus = parseWeatherStatus(model.weatherCode),
            windDirection = parseWindDirection(model.windDirection10m),
            windSpeed = model.windSpeed10m,
            isDay = model.isDay == 1,
        )

    private fun parseTime(
        time: Long,
        timeZone: String,
    ): String = Util.formatUnixToCustom(time, timeZone)

    private fun parseWeatherStatus(code: Int): WeatherInfoItem = Util.getWeatherInfo(code)

    private fun parseWindDirection(windDirection: Double): String = Util.getWindDirection(windDirection)
}
