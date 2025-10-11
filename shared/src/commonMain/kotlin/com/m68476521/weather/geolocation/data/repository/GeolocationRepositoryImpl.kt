package com.m68476521.weather.geolocation.data.repository

import GeoLocationEntity
import com.m68476521.weather.common.data.Mapper
import com.m68476521.weather.geolocation.data.locals.GeoLocationDao
import com.m68476521.weather.geolocation.data.remote.GeoLocationRemoteApiService
import com.m68476521.weather.geolocation.domain.models.GeoLocation
import com.m68476521.weather.geolocation.domain.repository.GeoLocationRepository
import com.m68476521.weather.utils.ApiErrorResponse
import com.m68476521.weather.utils.Response
import com.m68476521.weather.utils.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import toDomain

class GeolocationRepositoryImpl(
    private val geoLocationRemoteApiService: GeoLocationRemoteApiService,
    private val geoLocationDao: GeoLocationDao,
    private val geoLocationMapper: Mapper<GeoLocation, GeoLocationEntity>,
    private val externalScope: CoroutineScope,
) : GeoLocationRepository {
    override val geoLocation: Flow<GeoLocation?>
        get() {
            return geoLocationDao
                .geoLocation()
                .map {
                    geoLocationMapper.mapToDomainOrNull(it)
                }.shareIn(scope = externalScope, started = SharingStarted.Lazily)
        }

    override suspend fun upsertLocation(geoLocation: GeoLocation) {
        geoLocationDao.upsertGeoLocation(geoLocationMapper.mapFromDomain(geoLocation))
    }

    override fun fetchGeoLocation(query: String): Flow<Response<List<GeoLocation>, ApiErrorResponse>> =
        geoLocationRemoteApiService.searchLocation(query).map { response ->
            response.map { geoLocationDto ->
                geoLocationDto.toDomain()
            }
        }

    override suspend fun clearGeoLocation() {
        geoLocationDao.clearGeoLocation()
    }

    override suspend fun clear() {
        externalScope.cancel()
    }
}
