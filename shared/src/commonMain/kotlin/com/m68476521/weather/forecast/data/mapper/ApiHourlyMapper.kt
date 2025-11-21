package com.m68476521.weather.forecast.data.mapper

import com.m68476521.weather.forecast.data.remote.models.HourlyDto
import com.m68476521.weather.forecast.domain.models.Hourly
import org.hoods.forecastly.utils.Util
import org.hoods.forecastly.utils.WeatherInfoItem

class ApiHourlyMapper : ApiMapper<Hourly, HourlyDto> {
    override fun mapToDomain(
        model: HourlyDto,
        timeZone: String,
    ): Hourly =
        Hourly(
            temperature = model.temperature2m,
            time = parseTime(time = model.time, timeZone),
            weatherStatus = parseWeatherStatus(model.weatherCode),
        )

    private fun parseTime(
        time: List<Long>,
        timeZone: String,
    ): List<String> =
        time.map {
            Util.formatUnixToHour(it, timeZone)
        }

    private fun parseWeatherStatus(code: List<Int>): List<WeatherInfoItem> =
        code.map {
            Util.getWeatherInfo(it)
        }
}
