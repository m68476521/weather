package com.m68476521.weather.di

import GeoLocationEntity
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.m68476521.weather.common.data.HttpClientFactory
import com.m68476521.weather.common.data.Mapper
import com.m68476521.weather.forecast.data.mapper.ApiDailyWeatherMapper
import com.m68476521.weather.forecast.data.mapper.ApiHourlyMapper
import com.m68476521.weather.forecast.data.mapper.ApiMapper
import com.m68476521.weather.forecast.data.mapper.ApiWeatherMapper
import com.m68476521.weather.forecast.data.mapper.CurrentWeatherMapper
import com.m68476521.weather.forecast.data.remote.ForecastRemoteApiService
import com.m68476521.weather.forecast.data.remote.ForecastRemoteApiServiceImpl
import com.m68476521.weather.forecast.data.remote.models.CurrentDto
import com.m68476521.weather.forecast.data.remote.models.DailyDto
import com.m68476521.weather.forecast.data.remote.models.HourlyDto
import com.m68476521.weather.forecast.data.remote.models.WeatherDto
import com.m68476521.weather.forecast.data.repository.ForecastRepositoryImpl
import com.m68476521.weather.forecast.domain.models.CurrentWeather
import com.m68476521.weather.forecast.domain.models.Daily
import com.m68476521.weather.forecast.domain.models.Hourly
import com.m68476521.weather.forecast.domain.models.Weather
import com.m68476521.weather.forecast.domain.repository.ForecastRepository
import com.m68476521.weather.geolocation.data.locals.DatabaseFactory
import com.m68476521.weather.geolocation.data.locals.GeolocationDatabase
import com.m68476521.weather.geolocation.data.mapper.GeoLocationMapper
import com.m68476521.weather.geolocation.data.remote.GeoLocationRemoteApiService
import com.m68476521.weather.geolocation.data.remote.models.GeoLocationRemoteApiServiceImpl
import com.m68476521.weather.geolocation.data.repository.GeolocationRepositoryImpl
import com.m68476521.weather.geolocation.domain.models.GeoLocation
import com.m68476521.weather.geolocation.domain.repository.GeoLocationRepository
import com.m68476521.weather.ui.forecast.ForecastViewModel
import com.m68476521.weather.ui.home.HomeViewModel
import com.m68476521.weather.utils.provideExternalCoroutineScope
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
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
        singleOf(::ForecastRemoteApiServiceImpl).bind<ForecastRemoteApiService>()
        singleOf(::ApiDailyWeatherMapper).bind<ApiMapper<Daily, DailyDto>>()
        singleOf(::ApiHourlyMapper).bind<ApiMapper<Hourly, HourlyDto>>()
        singleOf(::ApiWeatherMapper).bind<ApiMapper<Weather, WeatherDto>>()
        singleOf(::CurrentWeatherMapper).bind<ApiMapper<CurrentWeather, CurrentDto>>()
        singleOf(::ForecastRepositoryImpl).bind<ForecastRepository>()

        viewModelOf(::HomeViewModel)
        viewModelOf(::ForecastViewModel)
    }
