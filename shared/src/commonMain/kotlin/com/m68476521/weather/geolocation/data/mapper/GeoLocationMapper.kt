package com.m68476521.weather.geolocation.data.mapper

import GeoLocationEntity
import com.m68476521.weather.common.data.Mapper
import com.m68476521.weather.geolocation.domain.models.GeoLocation
import org.hoods.forecastly.utils.K

class GeoLocationMapper : Mapper<GeoLocation, GeoLocationEntity> {
    override fun mapToDomainOrNull(model: GeoLocationEntity?): GeoLocation? =
        model?.run {
            GeoLocation(
                id = id,
                name = name,
                countryName = countryName,
                countryCode = countryCode,
                countryId = countryId,
                latitude = latitude,
                longitude = longitude,
                timeZone = timeZone,
                elevation = elevation,
                flagUrl = K.flagUrl(countryCode ?: ""),
            )
        }

    override fun mapFromDomain(domain: GeoLocation): GeoLocationEntity =
        domain.run {
            GeoLocationEntity(
                id = id,
                name = name,
                countryName = countryName,
                countryCode = countryCode,
                countryId = countryId,
                latitude = latitude,
                longitude = longitude,
                timeZone = timeZone,
                elevation = elevation,
            )
        }
}
