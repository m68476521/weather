package com.m68476521.weather.di

import GeoLocationEntity
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.m68476521.weather.common.data.HttpClientFactory
import com.m68476521.weather.common.data.Mapper
import com.m68476521.weather.geolocation.data.locals.DatabaseFactory
import com.m68476521.weather.geolocation.data.locals.GeolocationDatabase
import com.m68476521.weather.geolocation.data.mapper.GeoLocationMapper
import com.m68476521.weather.geolocation.data.remote.GeoLocationRemoteApiService
import com.m68476521.weather.geolocation.data.remote.models.GeoLocationRemoteApiServiceImpl
import com.m68476521.weather.geolocation.data.repository.GeolocationRepositoryImpl
import com.m68476521.weather.geolocation.domain.models.GeoLocation
import com.m68476521.weather.geolocation.domain.repository.GeoLocationRepository
import com.m68476521.weather.utils.provideExternalCoroutineScope
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule =
    module {
        single {
            get<DatabaseFactory>()
                .create()
                .setDriver(BundledSQLiteDriver())
                .build()
        }
        single { get<GeolocationDatabase>().geoLocationDao() }
        single { HttpClientFactory.create(get()) }
        single { provideExternalCoroutineScope() }.bind()
        singleOf(::GeoLocationRemoteApiServiceImpl).bind<GeoLocationRemoteApiService>()
        singleOf(::GeolocationRepositoryImpl).bind<GeoLocationRepository>()
        singleOf(::GeoLocationMapper).bind<Mapper<GeoLocation, GeoLocationEntity>>()
    }
