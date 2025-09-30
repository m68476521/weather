package com.m68476521.weather.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.m68476521.weather.geolocation.data.locals.DatabaseFactory
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
}